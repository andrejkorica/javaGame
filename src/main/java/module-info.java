module hr.unipu.memory {
    requires javafx.controls;
    requires javafx.fxml;


    opens hr.unipu.memory to javafx.fxml;
    exports hr.unipu.memory;
}