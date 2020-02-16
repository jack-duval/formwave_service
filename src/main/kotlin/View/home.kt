package View

import tornadofx.*

class home : View("My View") {
    override val root = borderpane {
        label { text("Hello!")}
    }
}
