package product;
import read_writer.DictionaryReadWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SaveProductGateway implements SaveProductGatewayInterface {

    /**
     * Add the product given the descriptions to the product.ser and IDtoProduct.ser files
     *
     * @param newProduct the new product string
     * @param productId  the ID of the product
     * @param tag        the category of the product
     */

    public void addProductToRepo(Product newProduct, String productId, String tag){

        DictionaryReadWriter rw = new DictionaryReadWriter();

        File file = new File("Main/product.ser");
        if (file.length() == 0) {
            HashMap<String, List<String>> emptyHashMap = new HashMap<>();
            ArrayList<String> productIDList = new ArrayList<>();
            productIDList.add(productId);
            emptyHashMap.put(tag, productIDList);
            rw.saveToFile("Main/product.ser", emptyHashMap);
        }
        else {

            HashMap<String, Object> productsSavedDict = rw.readFromFile("Main/product.ser");
            // {tag:[ID]}
            if (productsSavedDict.containsKey(tag)) {
                List<String> productIDList = (List<String>) productsSavedDict.get(tag);
                productIDList.add(productId);
                productsSavedDict.put(tag, productIDList);
                rw.saveToFile("Main/product.ser", productsSavedDict);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(newProduct.getId());
                productsSavedDict.put(tag, newList);
                rw.saveToFile("Main/product.ser", productsSavedDict);
            }
        }

        // {ID: product}
        File file2 = new File("Main/IdToProduct.ser");
        if (file2.length() == 0) {
            HashMap<String, Object> idToProductDict = new HashMap<>();
            idToProductDict.put(productId, newProduct);
            rw.saveToFile("Main/IdToProduct.ser", idToProductDict);
        }
        else {
            HashMap<String, Object> idToProductDict = rw.readFromFile("Main/IdToProduct.ser");
            idToProductDict.put(productId, newProduct);
            rw.saveToFile("Main/IdToProduct.ser", idToProductDict);
        }

    }

    /**
     * Saves changes to the specific product, to the IdtoProduct file.
    */
    public void saveChangedProduct(Product product) {

        DictionaryReadWriter rw = new DictionaryReadWriter();
        File file = new File("Main/IdToProduct.ser");
        if (!(file.length() == 0)) {
            HashMap<String, Object> productsSavedDict = rw.readFromFile("Main/IdToProduct.ser");
            String productId = product.getId();
            productsSavedDict.put(productId,product);
            rw.saveToFile("Main/IdToProduct.ser",productsSavedDict);
        }
    }
}