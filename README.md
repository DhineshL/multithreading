# multithreading

## Implementing Thread vs Extending Runnable

#### Which is a better approach ?
* if we extend the thread class, we cannot extend any other class. It is a usually a big disadvantage.
* However, a class may implement more than one interface, so while using Implements Runnable approach there is on restriction to extension of class now or in the future

## .join()
* Main thread as parent thread
  * when we start a program, usually the execution begins with main() method. This method runs on the main thread.This can be understood as the parent thread since it spawns the other threads.
* Independent execution of threads
  * when you create and start other threads, they run concurrently with the main thread unless instructed otherwise. So under normal circumstances, all threads run independent of each other. More explicitly, no other thread waits for other thread.
* what is join ?
  * Imagine threads to be lines of execution. So, when we call .join() on a certain thread, it means the parent thread, the main thread in this case (could be any thread which created the thread on which .join() is being called) is saying "Hey thread ,once you are done executing your task, join my flow of execution". It's like the parent thread waits for the completion of the child thread and then continues with it's execution.

## Daemon and User Threads
* On the basis of surface of execution, threads can be of two types
  * Daemon threads
  * User threads
* Daemon threads are intended to be helper threads which can run in backgroun and are of low priority Eg: GC thread
* Daemon threads are terminated by JVM when all other user threads are terminated