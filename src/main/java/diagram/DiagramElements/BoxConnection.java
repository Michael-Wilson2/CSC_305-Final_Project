package diagram.DiagramElements;

import java.io.Serializable;

public record BoxConnection(Box from, Box to, String type) implements Serializable { }
