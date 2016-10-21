package veeroute.bracket;

/*
This class is used as an integer holder for passing in lambdas.
Still trying to find a class with such functions in JavaSE packages.
 */
class Holder<T> {
    private T value;

    Holder(T value) {
        setValue(value);
    }

    T getValue() {
        return value;
    }

    void setValue(T value) {
        this.value = value;
    }
}
