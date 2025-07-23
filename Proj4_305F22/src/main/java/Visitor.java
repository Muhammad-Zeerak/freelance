import java.io.IOException;
import java.util.ArrayList;

public interface Visitor {
    public void accept(ParserVisitor parserVisitor) throws IOException;
}
