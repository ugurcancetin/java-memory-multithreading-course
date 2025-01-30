package org.gc;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.List;

public class Example {

  // Run with setting an initial heap size in VM args: -Xms256m -Xmx1024m
  public static void main(String[] args) throws InterruptedException {
    RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
    List<String> arguments = runtimeMxBean.getInputArguments();

    System.out.println("JVM Başlangıç Argümanları:");
    for (String arg : arguments) {
      System.out.println(arg);
    }

    // Java Runtime instance'ını al
    Runtime runtime = Runtime.getRuntime();

    // Bellek bilgilerini al
    long freeMemory = runtime.freeMemory();   // JVM içindeki serbest bellek

    // Sonuçları yazdır
    System.out.printf("Serbest JVM Belleği: %.2f MB%n", bytesToMB(freeMemory));
    System.out.printf("Total JVM Belleği: %.2f MB%n", bytesToMB(runtime.totalMemory()));
    System.out.println("-------------------------------------------------------------");

    List<Engineer> engineers = new ArrayList<>();

    for (int i = 0; i < 10000000; i++) {
      //      Thread.sleep(1); when observing the heap memory with visual VM
      engineers.add(new Engineer("Ugurcan"));
      engineers.get(i).setName("Cetin");
    }

    System.out.printf("Available memory when engineers hired Serbest JVM Belleği: %.2f MB%n", bytesToMB(freeMemory));
    System.out.printf("Total JVM Belleği: %.2f MB%n", bytesToMB(runtime.totalMemory()));
    System.out.println("-------------------------------------------------------------");

    Thread.sleep(1000);
    System.out.printf("1 second later Serbest JVM Belleği: %.2f MB%n",  bytesToMB(runtime.freeMemory()));
    System.out.printf("Total JVM Belleği: %.2f MB%n", bytesToMB(runtime.totalMemory()));
    System.out.println("-------------------------------------------------------------");

    System.gc();
    System.out.printf("after GC call Serbest JVM Belleği: %.2f MB%n", bytesToMB(runtime.freeMemory()));
    System.out.printf("Total JVM Belleği: %.2f MB%n", bytesToMB(runtime.totalMemory()));
  }

  // Byte'dan GB'ya dönüştürme metodu
  private static double bytesToMB(long bytes) {
    return bytes / (double) (1024 * 1024);
  }
}
