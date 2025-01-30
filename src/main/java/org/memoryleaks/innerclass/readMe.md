# Bellek Sızıntısı: İç Sınıflar (Inner Classes) Kullanımı

Java'da iç sınıfların (inner classes) kullanımı, doğru yönetilmediğinde bellek sızıntılarına yol açabilir. Bu bölümde, iç sınıfların neden olabileceği bellek sızıntılarını ve bu durumu önlemek için uygulanabilecek çözümleri detaylıca ele alacağız.

## İç Sınıflar ve Bellek Sızıntısı

### **Neden Bellek Sızıntısı Oluşur?**

Java'da iç sınıflar, otomatik olarak dış sınıfın (outer class) bir örneğine güçlü bir referans (strong reference) tutarlar. Bu durum, özellikle uzun ömürlü nesneler (örneğin, thread'ler veya executor servisleri) iç sınıfları referans aldığında, dış sınıfın nesnesinin çöp toplayıcı (Garbage Collector - GC) tarafından toplanmasını engeller. Sonuç olarak, dış sınıfın nesnesi bellekte kalmaya devam eder ve bu da bellek sızıntısına neden olur.

### **Yanlış Yaklaşım: İç Sınıflarla Bellek Sızıntısı**

İç sınıfların dış sınıfa olan otomatik güçlü referansları, dış sınıfın nesnesinin uzun ömürlü bir iç sınıf tarafından tutulması durumunda bellek sızıntısına yol açabilir. Örneğin:

- **Durum**: Dış sınıf, bir iç sınıf aracılığıyla uzun süre çalışan bir görev başlatır (örneğin, bir thread).
- **Sonuç**: İç sınıf, dış sınıfa güçlü bir referans tuttuğu için, dış sınıfın nesnesi GC tarafından toplanamaz ve bellekten atılamaz.

Bu durum, özellikle dinamik olarak oluşturulan ve uzun süreli görevler başlatan uygulamalarda sıkça karşılaşılabilir.

### **Doğru Yaklaşım: Statik İç Sınıflar ve Zayıf Referanslar**

Bellek sızıntısını önlemek için iç sınıfın dış sınıfa olan referansını kontrol altına almanız gerekir. Bunun için iki temel yöntem vardır:

1. **Statik İç Sınıflar Kullanmak**:
    - **Neden?**: Statik iç sınıflar, otomatik olarak dış sınıfın güçlü bir referansını tutmazlar.
    - **Nasıl?**: İç sınıfı `static` olarak tanımlayarak, dış sınıfa olan referansı kesebilirsiniz.

    ```java
    public class OuterClass {
        private String data;
    
        public OuterClass(String data) {
            this.data = data;
        }
    
        // Statik iç sınıf: dış sınıfa güçlü referans tutmaz
        public static class NonLeakyTask implements Runnable {
            private WeakReference<OuterClass> outerRef;
    
            public NonLeakyTask(OuterClass outer) {
                this.outerRef = new WeakReference<>(outer);
            }
    
            @Override
            public void run() {
                // Görev gerçekleştirme
                OuterClass outer = outerRef.get();
                if (outer != null) {
                    System.out.println("Görev tamamlandı: " + outer.data);
                } else {
                    System.out.println("Görev tamamlandı ancak OuterClass toplanmış.");
                }
            }
        }
    }
    ```

2. **Zayıf Referanslar (WeakReference) Kullanmak**:
    - **Neden?**: Zayıf referanslar, nesnelerin GC tarafından toplanmasına izin verir.
    - **Nasıl?**: İç sınıfın dış sınıfa olan referansını `WeakReference` ile tutarak, dış sınıfın nesnesinin GC tarafından toplanmasını sağlayabilirsiniz.

    ```java
    public class OuterClass {
        private String data;
    
        public OuterClass(String data) {
            this.data = data;
        }
    
        public class SafeTask implements Runnable {
            private WeakReference<OuterClass> outerRef;
    
            public SafeTask(OuterClass outer) {
                this.outerRef = new WeakReference<>(outer);
            }
    
            @Override
            public void run() {
                // Görev gerçekleştirme
                OuterClass outer = outerRef.get();
                if (outer != null) {
                    System.out.println("Görev tamamlandı: " + outer.data);
                } else {
                    System.out.println("Görev tamamlandı ancak OuterClass toplanmış.");
                }
            }
        }
    }
    ```

### **Nesnenin Bellekten Toplanıp Toplanmadığını Kontrol Etme**

Bir nesnenin bellekten toplanıp toplanmadığını anlamak için `WeakReference` kullanabilirsiniz. `WeakReference.get()` metodu, nesne toplanmışsa `null`, aksi takdirde nesnenin referansını döner.

**Adımlar:**

1. **Zayıf Referans Oluşturma**:
    ```java
    WeakReference<OuterClass> weakRef = new WeakReference<>(outerInstance);
    ```
2. **Güçlü Referansı Kaldırma**:
    ```java
    outerInstance = null;
    ```
3. **Garbage Collector'ı Tetikleme**:
    ```java
    System.gc();
    ```
4. **Zayıf Referansı Kontrol Etme**:
    ```java
    if (weakRef.get() == null) {
        System.out.println("OuterClass nesnesi bellekten toplandı.");
    }
    ```

### **Özet**

- **İç Sınıflar ve Güçlü Referanslar**: Non-static iç sınıflar, otomatik olarak dış sınıfa güçlü bir referans tutarlar. Bu durum, uzun ömürlü iç sınıflar tarafından tutulduklarında dış sınıfın bellekten atılmasını engelleyebilir.

- **Statik İç Sınıflar Kullanımı**: İç sınıfları statik (`static`) olarak tanımlayarak, dış sınıfa olan otomatik güçlü referansları ortadan kaldırabilirsiniz. Bu sayede, dış sınıfın nesnesi güçlü referanslar tarafından tutulmuyorsa GC tarafından toplanabilir.

- **Zayıf Referanslar (WeakReference)**: İç sınıfın dış sınıfa olan referansını `WeakReference` ile tutarak, dış sınıfın nesnesinin GC tarafından toplanmasını sağlayabilirsiniz. Bu yöntem, bellek sızıntılarını önlemek için etkili bir çözümdür.

- **Nesnenin Bellekten Toplanıp Toplanmadığını Kontrol Etme**: `WeakReference` kullanarak, nesnelerin GC tarafından toplanıp toplanmadığını programatik olarak kontrol edebilirsiniz.

### **İyi Uygulamalar**

1. **Statik İç Sınıfları Tercih Edin**:
    - İç sınıfları mümkün olduğunca statik (`static`) olarak tanımlayın. Bu, otomatik güçlü referansları önler ve bellek sızıntısı riskini azaltır.

2. **Zayıf Referanslar Kullanın**:
    - İç sınıfın dış sınıfa olan referansını `WeakReference` ile tutun. Bu, dış sınıfın nesnesinin GC tarafından toplanmasını sağlar.

3. **Profiling Araçları Kullanın**:
    - Uygulamanızda bellek sızıntılarını tespit etmek için VisualVM, JProfiler, YourKit gibi profil oluşturucular kullanın. Bu araçlar, bellek kullanımını görsel olarak izlemenizi sağlar.

4. **Nesne Yaşam Döngülerini Yönetme**:
    - Nesnelerin yaşam döngülerini dikkatlice yönetin. Uzun ömürlü nesnelerin (thread'ler, executor'lar vb.) kısa ömürlü nesnelere referans tutmasını önleyin.

5. **İyi Tasarım İlkelerini Takip Edin**:
    - Uygulamanızın mimarisini bellek yönetimi göz önünde bulundurarak tasarlayın. Gereksiz referansları ortadan kaldırın ve nesnelerin bellekten serbest bırakılmasını kolaylaştırın.

Bu yöntemler ve iyi uygulamalar, Java'da iç sınıfların neden olabileceği bellek sızıntılarını önlemenize yardımcı olacak ve uygulamanızın performansını artıracaktır.
