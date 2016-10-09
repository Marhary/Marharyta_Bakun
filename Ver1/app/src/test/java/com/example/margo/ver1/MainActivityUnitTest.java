package com.example.margo.ver1;

import android.test.suitebuilder.annotation.SmallTest;
import android.view.TextureView;

import com.example.margo.ver1.logic.Visible;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainActivityUnitTest {

    @Mock
    private TextureView text;

    @SmallTest
    public void testchecker() {
        MainActivity ma = new MainActivity();
        boolean result = ma.checker("MOM");
        assertEquals(true, result);
    }

    @Test
    public void iterator() {
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        String result = i.next() + " " + i.next();
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        assertEquals(1, c.compareTo("Test"));
    }

    @Test
    public void testReturnValueDependentOnMethodParameter() {
        final String MOCKITO = "mockito";
        final String ESPRESSO = "espresso";
        final int MOCKITO_INT = 1;
        final int ESPRESSO_INT = 2;

        Comparable comparable = mock(Comparable.class);
        when(comparable.compareTo(MOCKITO)).thenReturn(MOCKITO_INT);
        when(comparable.compareTo(ESPRESSO)).thenReturn(ESPRESSO_INT);

        assertEquals(MOCKITO_INT, comparable.compareTo(MOCKITO));
        assertEquals(ESPRESSO_INT, comparable.compareTo(ESPRESSO));
    }

    @Test(expected = IOException.class)
    public void IOExceptionTest() throws IOException {

        OutputStream mockStream = mock(OutputStream.class);
        doThrow(new IOException()).when(mockStream).close();

        OutputStreamWriter streamWriter = new OutputStreamWriter(mockStream);
        streamWriter.close();
    }

    @Test
    public void BtnVisibilityTest() {
        Visible vis = new Visible();
        Visible visible = Mockito.spy(vis);
        doReturn(false).when(visible).isVisibility();
        System.err.println(visible.isVisibility());
        assertEquals(visible.isVisibility(), false);
    }
}
