package gradle.test

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.util.Duration
import tornadofx.*
import java.time.LocalDate

class FormView : View() {
    val progressP  = SimpleDoubleProperty()
    val rememberMe = SimpleBooleanProperty()
    val username   = SimpleStringProperty("")
    val password   = SimpleStringProperty("")
    val message    = SimpleStringProperty("This will show messages")
    val birthDate  = SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 1, 1))
    val sex        = SimpleStringProperty()
    val sexChoices = FXCollections.observableArrayList("Male", "Female", "Not specified")

    override fun onDock() {
        setWindowMinSize(300, 300)
    }

    override val root = vbox {
        shortcut("Ctrl+Q") {
            close()
        }

        form {
            fieldset {
                 field() {
                    checkbox("Remember me", rememberMe) {
                        action {
                            message.value = "Remember me: ${rememberMe.value}"
                        }
                    }
                 }

                field("Username") {
                    textfield(username)
                }

                field("Password") {
                    passwordfield(password)
                }

                field("Sex") {
                    combobox(sex, values = sexChoices) {
                        useMaxWidth = true
                    }
                }

                field("Date of birth") {
                    datepicker(birthDate)
                }
            }

            button("LOGIN") {
                useMaxWidth = true

                shortcut("Ctrl+L")

                tooltip("Log me in") {
                    showDelay = Duration(300.0)
                }

                action {
                    message.value = "Logging in as ${username.value} ${password.value} ${sex.value}"

                    runAsync {
                        for (i in 1..100) {
                            progressP.value = i.toDouble() / 100.0
                            Thread.sleep(30)
                        }
                    }
                }
            }
        }

        label(message) {
            padding = Insets(10.0)
        }

        progressbar(progressP) {
            useMaxWidth = true
            padding = Insets(10.0)
        }
    }
}