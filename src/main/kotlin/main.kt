
import kotlinx.coroutines.*
import kotlin.coroutines.*



fun main() = runBlocking {
    val job = CoroutineScope(EmptyCoroutineContext).launch {
        launch {
            delay(500)
            println("ok") // <--Нет, так как родительская карутина отменяется до запуска дочерних
        }
        launch {
            delay(500)
            println("ok")
        }
    }
    delay(100)
    job.cancelAndJoin()
}
//fun main() = runBlocking {
//    val job = CoroutineScope(EmptyCoroutineContext).launch {
//        val child = launch {
//            println(isActive)
//            delay(500)
//            println("ok") // <--Нет, так как карутина была отменена раньше, чем отработал println
//        }
//        launch {
//            println(isActive)
//            delay(500)
//            println("ok")
//        }
//        delay(100)
//        child.cancel()
//    }
//    delay(100)
//    job.join()
//}
//fun main() {
//    with(CoroutineScope(EmptyCoroutineContext)) {
//        try {
//            launch {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <-- Нет, так как исключение не перехвачено в дочерней корутине и соответственно она аварийно завершается, завершается так же и родительская корутина, try catch должен быть внутри метода launch
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            coroutineScope {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--Да, так как coroutineScope перехватывает ошибки и предоставляет их родительской, далее родительская корутина обрабатывает их в try catch и сохраняет свое активное состояние
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            supervisorScope {
//                throw Exception("something bad happened")
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--Да, так как supervisorScope создает реализацию job которая не влияет на родительскую
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            coroutineScope {
//                launch {
//                    delay(500)
//                    throw Exception("something bad happened") // <-- Нет, так как в coroutineScope первой поступает исключние из второй дочерней корутины и на этом coroutineScope завершает свою работу передавая информацию об исключении в родительскую корутину
//                }
//                launch {
//                    throw Exception("something bad happened")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        try {
//            supervisorScope {
//                launch {
//                    delay(500)
//                    throw Exception("something bad happened") // <--Да, так как supervisorScope не затрагивая работу дочерних и соседних корутин, обе дочерние корутины завершают свою работу
//                }
//                launch {
//                    throw Exception("something bad happened")
//                }
//            }
//        } catch (e: Exception) {
//            e.printStackTrace() // <--Нет, так как блок try catch должен находиться внутри supervisorScope, для каждой дочерней корутины
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        CoroutineScope(EmptyCoroutineContext).launch {
//            launch {
//                delay(1000)
//                println("ok") // <-- Нет, так как исключение мы получаем быстрей, чем дочерние корутины успевают завершить твою работу
//            }
//            launch {
//                delay(500)
//                println("ok")
//            }
//            throw Exception("something bad happened")
//        }
//    }
//    Thread.sleep(1000)
//}
//fun main() {
//    CoroutineScope(EmptyCoroutineContext).launch {
//        CoroutineScope(EmptyCoroutineContext + SupervisorJob()).launch {
//            launch {
//                delay(1000)
//                println("ok") // <-- нет, так как исключение происходит в родительской корутине SupervisorJob, отменяются дочерние корутины
//            }
//            launch {
//                delay(500)
//                println("ok")
//            }
//            throw Exception("something bad happened")
//        }
//    }
//    Thread.sleep(1000)
//}