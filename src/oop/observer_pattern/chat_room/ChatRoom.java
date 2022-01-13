package oop.observer_pattern.chat_room;
import java.util.*;

public class ChatRoom implements Observable{
    List<Observer> observers = new ArrayList<>();
    Map<Observer, String> map = new HashMap<>();
    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void delete(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void _notify() {
        observers.forEach(observer -> observer.showMsg(observer));
    }

    public String getMsg(Observer user){
        StringBuilder sb = new StringBuilder();
        for (Observer observer : map.keySet()){
            if(user == observer) continue;
            String content = observer.getName() + " : " + map.get(observer);
            sb.append(content);
        }
        return sb.toString();
    }

    public void addMsg(Observer observer, String content){
        map.put(observer, map.getOrDefault(observer, "") + content);
        _notify();
    }
}
