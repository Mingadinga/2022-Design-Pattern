public class LineNumberDecorator extends Decorator{
    public LineNumberDecorator(Item item) {
        super(item);
    }

    @Override
    public int getLinesCount() {
        return item.getLinesCount();
    }

    @Override
    public int getMaxLength() {
        return item.getMaxLength();
    }

    @Override
    public int getLength(int index) {
        return item.getLength(index);
    }

    @Override
    public String getString(int index) {
        return String.format("%02d", index) + ": " + item.getString(index);
    }
}
