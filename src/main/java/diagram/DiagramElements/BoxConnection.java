package diagram.DiagramElements;

import java.io.Serializable;

public record BoxConnection(Box from, Box to, String type) implements Serializable { }

// TODO: draw in different colors/styles based on type?
//  should we have subclasses instead of type?
