package org.example;

import org.example.graphicEditor.*;

public class Main {
    public static void main(String[] args) {
        //graphicEditor
        Shape circle=new Circle("Circle",14.0);
        Shape triangle=new Triangle("Triangle",4.0,4.0,6.0);
        Shape square=new Square("Square",5.0);
        Shape rectangle = new Rectangle("Rectangle", 4.0,6.0);
        Shape rhombus=new Rhombus("Rhombus",10.0,45.0);
        Shape pentagon=new Pentagon("Pentagon",4.0,4.0,4.0,4.0,4.0);
        ShapePrinter printer=new ShapePrinter();
        printer.printShapeName(circle);
        printer.printShapeName(triangle);
        printer.printShapeName(square);
        printer.printShapeName(rectangle);
        printer.printShapeName(rhombus);
        printer.printShapeName(pentagon);
    }
}