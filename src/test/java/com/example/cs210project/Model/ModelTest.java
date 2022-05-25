package com.example.cs210project.Model;
import org.junit.jupiter.api.Test;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void writeStockToBinaryFile()
    {
        File file = new File("C:\\Users\\TheoWalcott\\Desktop\\Robin\\IntelJ\\CS210Project4\\RecipeList.dat");
        assertTrue(file.exists());
    }

}