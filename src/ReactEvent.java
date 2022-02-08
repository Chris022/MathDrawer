

import java.util.ArrayList;
import java.util.function.Consumer;

public class ReactEvent<T> {

    private ArrayList<Consumer<T>> subscribers = new ArrayList<Consumer<T>>();

    public void subscribe(Consumer<T> function){
        subscribers.add(function);
    }

    public void fire(T arg){
        subscribers.forEach(x -> x.accept(arg));
    }

    public void unssubcribe(Consumer<T> function){
        subscribers.remove(function);
    }
    
}