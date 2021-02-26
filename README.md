# RxJavaApplication : POC project on RxJava

Offical site for RxJava - http://reactivex.io/

<b>RxJava</b> is a Java VM implementation of ReactiveX provides implementations for reactive programming concept for different programming languages.
 
</b>RxAndroid</b> is just a layer on top of RxJava which provides android specific support.</b>Hence, RxAndroid does not replace RxJava.</b>

## Why RxJava?
1) RxJava makes multi-threading very easy.
2) It is reactive based - Codes are not active until it knows it`s end mean.
3) It provides extensibility to our code - RxJava can be extended with customes operators.
4) RxJava is composable - RxJava can be combined to use one or more complex operations.
5) Error handling becomes much easier with RxJava.
6) RxJava provide simple mechanism to terminate background processes rapidly.

## Getting Started
Following are the two main components of RxJava :
1) Observables - Instance of Observable class - observe data streams and emit them to subscribed observers.
2) Observers - Instance of Observer Interface - consume data emitted by observables

*Note : One Observable can have many observers. And, if there is no subscription observable will not emit data.*

Important Observer methods : 
1) <b>onNext()</b> - gets called each time the observale emits data.
2) <b>onError()</b> - gets called if any error occured.
3) <b>onComplete()</b> - gets called when data emission is completed.

<b>Schedulers</b> - for handling Concurrency and multi-threading - A scheduler can be recognized as a thread pool managing one or more threads. Whenever a Scheduler needs to execute a task, it will take a thread from its pool and run the task in that thread.

Commonly used Schedulers :
1) Schedulers.io()
2) AndroidSchedulers.mainThread()
3) Schedulers.newThread()
4) Schedulers.single()
5) Schedulers.trampoline()
6) Schedulers.from(Executor executor)

To modify data - Use <b>Operators</b>
