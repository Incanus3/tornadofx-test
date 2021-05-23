package gradle.test

import tornadofx.*

class MyView1: View() {
    override val root = vbox {
        button("Go to MyView2") {
            action {
                val view = find<MyView2>(mapOf(MyView2::testParam to "test param"))

                replaceWith(view, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
            }
        }
    }

    override fun onDock() {
        println("Docking MyView1")
    }

    override fun onUndock() {
        println("Undocking MyView1")
    }
}

class MyView2: View() {
    val testParam: String by param("default")

    override val root = vbox {
        button("Go to MyView1") {
            action {
                replaceWith(MyView1::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }
    }

    override fun onDock() {
        println("Docking MyView2 with '${this.testParam}'")
    }

    override fun onUndock() {
        println("Undocking MyView2")
    }
}

class RootView: View() {
    override val root = borderpane {
        top {
            label("some label")
        }

        bottom<MyView1>()
    }

    override fun onDock() {
        println("Docking RootView")
        println(this.primaryStage.title)
        // this.primaryStage.title = "Primary Stage"
    }

    override fun onUndock() {
        println("Undocking RootView")
    }
}