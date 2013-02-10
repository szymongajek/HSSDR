// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Start.java

package controller;

import editor.MainWindow;

// Referenced classes of package controller:
//            Controller

public class Start
{

    public Start()
    {
    }

    public static void main(String args[])
    {
        Controller controller = new Controller();
        MainWindow mw = new MainWindow(controller);
        controller.init(mw, mw.getHyperGraphEditor(), mw.getValidationEditor());
        mw.setVisible(true);
        System.out.println("-------START-----------");
    }
}
