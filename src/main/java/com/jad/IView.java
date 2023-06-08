package com.jad;

public interface IView {
    String display();

    void setModel(IModel model);

    void setController(IController controller);
}
