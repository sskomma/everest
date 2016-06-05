package com.questions.salesforce.component;

public class Installer
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Application installer = Application.getInstance();
        installer.install("C:\\test1_input.txt");

    }

}
