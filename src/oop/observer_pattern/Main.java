package oop.observer_pattern;

public class Main {
   /* todo
   I still don't like the idea of passing the observable to the observers as parameter
   into the constructor of the observers. feels like you are giving the observers more than
   they need to know the data!
    I think it would be better to send the updated data via update(newData) method..

            - consider the following use case:-

            * At the same concrete class "weather station", they are more than one sensor..
            for wind speed, another for humidity, and of course the one for temperature..

            * Now Imagine that there are some observers which are interested only in the humidity,
             and others in wind speed only..
    we can do an observer pattern for each piece of data by itself,
    with the update method signature changed based on the wanted data..
            * so we can have update (Humidity newHumidityData),
             update (WindSpeed newWindSpeed), etc .. each one into its own observer interface of course..
            * what I'm saying is, you observe pieces of data, not the whole object :) ..
            that way I prefer passing the new data via update :) ..*/

    /*
    * use cases
    * chat room
    * news subscription
    * twitter following
    * weather station
    * event trigger
    *
    *
    * This pattern  perfectly suits any process where data arrives from some input,
    * rather isn`t available to the CPU at startup, yet can arrive "at random"
    * (HTTP requests, GPIO data, user input from keyboard/mouse/..., distributed databases and blockchains, ...).
    * */

    /*
    * The observer pattern is a software design pattern in which an object, named the subject/observable,
    *  maintains a list of its dependents, called observers,
    * and notifies them automatically of any state changes, usually by calling one of their methods.
    *
    *
    * */

    /*
    * https://en.wikipedia.org/wiki/Observer_pattern#:~:text=The%20observer%20pattern%20is%20a,calling%20one%20of%20their%20methods.
    * Coupling and typical pub-sub implementations [VERY GOOD READ]
    * */

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        PhoneDisplay phoneDisplay = new PhoneDisplay(weatherStation);
        LcdDisplay lcdDisplay = new LcdDisplay(weatherStation);

        weatherStation.add(phoneDisplay);
        weatherStation.add(lcdDisplay);

        weatherStation._notify();
    }

}
