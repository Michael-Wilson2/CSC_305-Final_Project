package project.diagram.DiagramElements;

import java.io.Serializable;

/** Represents a box (class) connection.
 *
 * @author Michael Wilson
 * @author Andrew Kulakovsky
 * @version 1.0
 */

public record BoxConnection(Box from, Box to, String type) implements Serializable { }
