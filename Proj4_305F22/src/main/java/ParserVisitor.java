import java.io.IOException;
import java.util.ArrayList;

public interface ParserVisitor {
    public void visit(Parser parser) throws IOException;
}
