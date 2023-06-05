package com.jad;

public interface IView {
    void display();
    void setModel(IModel model);
    void setController(IController controller);
}
