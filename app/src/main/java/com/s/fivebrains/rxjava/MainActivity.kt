package com.s.fivebrains.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Observable
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.just("Hello Sunil").subscribe{value-> println("===========$value")}


        Observable.fromArray("Marry", "John", "Peter")
            .subscribe(
                { value -> println("===Received:====== $value") }, // onNext
                { error -> println("Error: $error") },    // onError
                { println("Completed!") }                 // onComplete
            )


        val numbers = listOf("one", "two", "three", "four")
        Observable.fromIterable(numbers)
            .subscribe(
            { value-> println("====$value") },
                {error-> println("$error")},
        { print("Completed")}
        )


        // get Iterate list by Observable
        getObservableFromList(numbers).subscribe{
            println("====Received===$it")
        }


        // Interval

        Observable.intervalRange(
            10L ,   //Start
             15L,   //Count
              0L, // Initial Count
               1L ,   // Period
                       TimeUnit.SECONDS
        ).subscribe{ println("====$it")}
    }


    fun getObservableFromList(mylist:List<String>)=
        Observable.create<String>{emitter->
            mylist.forEach{kind->
             if(kind==""){
                emitter.onError(Exception("There are no values"))
            }
            emitter.onNext(kind)
        }
            emitter.onComplete()
        }
    }





