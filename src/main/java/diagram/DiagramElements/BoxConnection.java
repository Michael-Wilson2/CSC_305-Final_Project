package diagram.DiagramElements;

import java.io.Serializable;

// TODO: Make connection type an enum
public record BoxConnection(Box from, Box to, String type) implements Serializable { }
