package intermediate;

import com.sandwich.koan.Koan;

import java.util.*;

import static com.sandwich.koan.constant.KoanConstants.__;
import static com.sandwich.util.Assert.assertEquals;
import static com.sandwich.util.Assert.assertTrue;


public class AboutCollections {

    @Koan
    public void usingAnArrayList() {
        // List = interface
        // The generic syntax and special generic cases will be handled in
        // AboutGenerics. We just use <String> collections here to keep it
        // simple.
        List<String> list = new ArrayList<String>();
        // ArrayList: simple List implementation
        list.add("Chicken");
        list.add("Dog");
        list.add("Chicken");
        assertEquals(list.get(0),"Chicken" );
        assertEquals(list.get(1), "Dog");
        assertEquals(list.get(2), "Chicken");
        // assertEquals sprawdza czy obiekt z indeksu 0 rowna sie Chickenowi
    }

    @Koan
    public void usingAQueue() {
        // Queue = interface
        Queue<String> queue = new PriorityQueue<String>();
        // PriorityQueue: simple queue implementation
        queue.add("Cat");
        queue.add("Dog");
        assertEquals(queue.peek(),"Cat");       // szczyt kolejki
        assertEquals(queue.size(), 2);          // rozmiar calej kolejki
        assertEquals(queue.poll(), "Cat");      // wyciagamy pierwszy element, czyli Cat
        assertEquals(queue.size(), 1);          // aktualny rozmiar kolejki
        assertEquals(queue.poll(), "Dog");      // wyciagamy kolejmny element czyli dog
        assertEquals(queue.isEmpty(), true);    // zwraca true bo nie ma juz obiektow w kolejce
    }

    @Koan
    public void usingABasicSet() {
        Set<String> set = new HashSet<String>();
        set.add("Dog");
        set.add("Cat");
        set.add("Dog");
        assertEquals(set.size(), 2);
        assertEquals(set.contains("Dog"), true);        // sprawdza czy jest dog
        assertEquals(set.contains("Cat"), true);        // sprawdza czy jest cat
        assertEquals(set.contains("Chicken"),false);    // sprawdza czy jest chicke
    }

    @Koan
    public void usingABasicMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("first key", "first value");
        map.put("second key", "second value");
        map.put("first key", "other value");
        assertEquals(map.size(), 2);
        assertEquals(map.containsKey("first key"), true);                   // czy mapa zawiera klucz first key
        assertEquals(map.containsKey("second key"), true);                  // czy mapa zawiera klucz second key
        assertEquals(map.containsValue("first value"), false);              // czy mapa zawiera wartosc first value? nie bo sie napisal
        assertEquals(map.get("first key"), "other value");
    }

    @Koan
    public void usingBackedArrayList() {
        String[] array = {"a", "b", "c"};
        List<String> list = Arrays.asList(array);
        list.set(0, "x");
        assertEquals(array[0], "x");
        array[0] = "a";
        assertEquals(list.get(0), "a");
        // Just think of it as quantum state teleportation...
    }

    @Koan
    public void usingBackedSubMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        map.put("a", "Aha");
        map.put("b", "Boo");
        map.put("c", "Coon");
        map.put("e", "Emu");
        map.put("f", "Fox");
        SortedMap<String, String> backedMap = map.subMap("c", "f");         // tworzy mape od "c" do "f" bez "f" czyli dwa elementy
        assertEquals(backedMap.size(), 2);                              // rozmiar mapy backedMap
        assertEquals(map.size(), 5);                                    // rozmiar mapy map
        backedMap.put("d", "Dog");                                          // dolozenie pola d dog
        assertEquals(backedMap.size(), 3);                              // rozmiar mapu backedMap po dolozeniu d dog
        assertEquals(map.size(), 6);                                    // backedMap rozszerza mape map, dlatego zawiera juz 6 elmentow a nie 5
        assertEquals(map.containsKey("d"), true);                       //czy mapa map zawiera klucz "d", tak bo d dog
        // Again: backed maps are just like those little quantum states
        // that are connected forever...
    }

    @Koan
    public void differenceBetweenOrderedAndSorted() {
        TreeSet<String> sorted = new TreeSet<String>();             // TreeSet jest domyslnie sortowany
        sorted.add("c");
        sorted.add("z");
        sorted.add("a");
        assertEquals(sorted.first(), "a");          // podaj najmniejszą wartosc
        assertEquals(sorted.last(),"z");            // podaj ostatnia wartosc
        // Look at the different constructors for a TreeSet (or TreeMap)
        // Ponder how you might influence the sort order. Hold that thought
        // until you approach AboutComparison

        LinkedHashSet<String> ordered = new LinkedHashSet<String>();        // LinkedHashSet jest uporzadkowany wg elementow dodania
        ordered.add("c");
        ordered.add("z");
        ordered.add("a");
        StringBuffer sb = new StringBuffer();
        for (String s : ordered) {
            sb.append(s);
        }
        assertEquals(sb.toString(), "cza");
    }
}
