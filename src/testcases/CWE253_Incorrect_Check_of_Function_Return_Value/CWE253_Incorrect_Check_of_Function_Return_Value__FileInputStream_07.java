/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE253_Incorrect_Check_of_Function_Return_Value__FileInputStream_07.java
Label Definition File: CWE253_Incorrect_Check_of_Function_Return_Value__FileInputStream.label.xml
Template File: point-flaw-07.tmpl.java
*/
/*
* @description
* CWE: 253 Incorrect Check of Function Return Value
* Sinks:
*    GoodSink: Check the return value from FileInputStream.read() and handle possible errors
*    BadSink : Check the return value of FileInputStream.read() for the wrong value
* Flow Variant: 07 Control flow: if(privateFive==5) and if(privateFive!=5)
*
* */

package testcases.CWE253_Incorrect_Check_of_Function_Return_Value;

import testcasesupport.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.logging.Level;

public class CWE253_Incorrect_Check_of_Function_Return_Value__FileInputStream_07 extends AbstractTestCase
{
    /* The variable below is not declared "final", but is never assigned
     * any other value so a tool should be able to identify that reads of
     * this will always give its initialized value.
     */
    private int privateFive = 5;

    public void bad() throws Throwable
    {
        if (privateFive == 5)
        {
            FileInputStream fis = null;
            try
            {
                int bytesToRead = 1024;
                byte[] byteArray = new byte[bytesToRead];
                fis = new FileInputStream("c:\\file.txt");
                /* FLAW: Incorrect check on result of read().  Should check if the return value is -1 or is less than bytesToRead. */
                if (fis.read(byteArray) == 0)
                {
                    IO.writeLine("Error reading file.");
                }
                else
                {
                    IO.writeLine(new String(byteArray, "UTF-8"));
                }
            }
            catch (FileNotFoundException exceptFileNotFound)
            {
                IO.logger.log(Level.WARNING, "FileNotFoundException opening file", exceptFileNotFound);
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "IOException reading file", exceptIO);
            }
            finally
            {
                try
                {
                    if (fis != null)
                    {
                        fis.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "IOException closing FileInputStream", exceptIO);
                }
            }
        }
    }

    /* good1() changes privateFive==5 to privateFive!=5 */
    private void good1() throws Throwable
    {
        if (privateFive != 5)
        {
            /* INCIDENTAL: CWE 561 Dead Code, the code below will never run */
            IO.writeLine("Benign, fixed string");
        }
        else
        {

            FileInputStream fis = null;

            try
            {
                int bytesToRead = 1024;
                byte[] byteArray = new byte[bytesToRead];

                fis = new FileInputStream("c:\\file.txt");

                int numberOfBytesRead = fis.read(byteArray);

                /* FIX: Check the return value of read() and handle possible errors */
                if (numberOfBytesRead == -1)
                {
                    IO.writeLine("The end of the file has been reached.");
                }
                else
                {
                    if (numberOfBytesRead < bytesToRead)
                    {
                        IO.writeLine("Could not read " + bytesToRead + " bytes.");
                    }
                    else
                    {
                        IO.writeLine(new String(byteArray, "UTF-8"));
                    }
                }
            }
            catch (FileNotFoundException exceptFileNotFound)
            {
                IO.logger.log(Level.WARNING, "FileNotFoundException opening file", exceptFileNotFound);
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "IOException reading file", exceptIO);
            }
            finally
            {
                try
                {
                    if (fis != null)
                    {
                        fis.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "IOException closing FileInputStream", exceptIO);
                }
            }

        }
    }

    /* good2() reverses the bodies in the if statement */
    private void good2() throws Throwable
    {
        if (privateFive == 5)
        {
            FileInputStream fis = null;
            try
            {
                int bytesToRead = 1024;
                byte[] byteArray = new byte[bytesToRead];
                fis = new FileInputStream("c:\\file.txt");
                int numberOfBytesRead = fis.read(byteArray);
                /* FIX: Check the return value of read() and handle possible errors */
                if (numberOfBytesRead == -1)
                {
                    IO.writeLine("The end of the file has been reached.");
                }
                else
                {
                    if (numberOfBytesRead < bytesToRead)
                    {
                        IO.writeLine("Could not read " + bytesToRead + " bytes.");
                    }
                    else
                    {
                        IO.writeLine(new String(byteArray, "UTF-8"));
                    }
                }
            }
            catch (FileNotFoundException exceptFileNotFound)
            {
                IO.logger.log(Level.WARNING, "FileNotFoundException opening file", exceptFileNotFound);
            }
            catch (IOException exceptIO)
            {
                IO.logger.log(Level.WARNING, "IOException reading file", exceptIO);
            }
            finally
            {
                try
                {
                    if (fis != null)
                    {
                        fis.close();
                    }
                }
                catch (IOException exceptIO)
                {
                    IO.logger.log(Level.WARNING, "IOException closing FileInputStream", exceptIO);
                }
            }
        }
    }

    public void good() throws Throwable
    {
        good1();
        good2();
    }

    /* Below is the main(). It is only used when building this testcase on
     * its own for testing or for building a binary to use in testing binary
     * analysis tools. It is not used when compiling all the testcases as one
     * application, which is how source code analysis tools are tested.
     */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}