package pt.isel.ls.print;

import pt.isel.ls.handler.ResultView;

import java.io.IOException;
import java.io.OutputStream;

import static pt.isel.ls.utils.UtilMethods.getBytes;

public class HtmlPrint implements PrintInterface {
    private final ResultView resultView;

    public HtmlPrint(ResultView resultView) {
        this.resultView = resultView;
    }

    @Override
    public void printTo(OutputStream outputStream) throws IOException {
        outputStream.write(getBytes(resultView.htmlOutput()));
        outputStream.flush();
        outputStream.close();
    }

}
