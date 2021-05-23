package gradle.test

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.stage.StageStyle
import tornadofx.*

class ViewWithModal: View() {
    val controller: MyController by inject()
    val input = SimpleStringProperty()

    override val root = form {
        fieldset {
            // field("input") {
            //     textfield(input)
            // }

            // field("list") {
            //     listview(controller.values)
            // }

            button("submit") {
                action {
                    // controller.writeToDb(input.value)
                    // input.value = ""

                    find<MyFragment>().openModal(stageStyle = StageStyle.UNDECORATED)
                }
            }
        }
    }
}

class MyFragment: Fragment() {
    override val root = label("This is a popup")
}

class MyController: Controller() {
    val values = FXCollections.observableArrayList("Alpha", "Beta", "Gamma", "Delta")

    fun writeToDb(inputValue: String) {
        println("Writing $inputValue to database!")
    }
}