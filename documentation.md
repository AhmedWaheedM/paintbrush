# Paint Brush Application - Complete Documentation

## Project Overview
**Paint Brush** is a Java-based desktop drawing application developed for ITI (Information Technology Institute). It provides a comprehensive set of drawing tools similar to Microsoft Paint, allowing users to create, edit, and save digital artwork with various shapes, colors, and drawing modes.

## Table of Contents
1. [Architecture](#architecture)
2. [Core Components](#core-components)
3. [Features](#features)
4. [Design Patterns](#design-patterns)
5. [Package Structure](#package-structure)
6. [User Interface](#user-interface)
7. [File Operations](#file-operations)
8. [How It Works](#how-it-works)

---

## Architecture

The application follows a **Model-View-Controller (MVC)** inspired architecture with clear separation of concerns:

- **UI Layer**: `Mainframe` and `DrawingPanel` handle user interaction and rendering
- **Model Layer**: Shape classes represent drawable objects
- **Controller Layer**: Event handlers manage user input and state changes
- **Utility Layer**: Helper classes for object creation and file operations

### Technology Stack
- **Language**: Java 17
- **GUI Framework**: Java Swing
- **Graphics**: Java AWT Graphics API
- **Persistence**: Java Serialization

---

## Core Components

### 1. Main Application Entry Point
**Class**: `PaintBrushApp`
- Located in: `com.iti.paintbrush.main`
- Initializes the application window
- Sets up the main frame and displays the GUI

### 2. Main Window (Mainframe)
**Class**: `Mainframe` extends `JFrame`
- **Location**: `com.iti.paintbrush.ui`
- **Dimensions**: 1350x600 pixels
- **Responsibilities**:
  - Creates and manages the toolbar with all drawing controls
  - Builds the menu bar for file operations
  - Hosts the drawing panel
  - Manages user interface state and interactions

**Key UI Elements**:
- **Actions Row**: Undo, Redo, Clear, Reset buttons
- **Tools Row**: Contains color palette, shape selection, draw modes, and thickness slider
- **Menu Bar**: File operations (Save, Load, Export, Import)

### 3. Drawing Panel
**Class**: `DrawingPanel` extends `JPanel`
- **Location**: `com.iti.paintbrush.ui`
- **Responsibilities**:
  - Handles all mouse events (press, drag, release)
  - Manages the collection of drawn shapes
  - Renders all shapes and background images
  - Maintains drawing state (color, mode, thickness)
  - Implements undo/redo functionality

**State Management**:
- `shapes`: ArrayList of all drawn shapes (active drawing history)
- `removedShapes`: ArrayList for redo functionality
- `backupShapes`: Backup for reset/undo operations
- `currentShape`: The shape being actively drawn
- `backgroundImage`: Optional imported background image
- `currentColor`: Selected drawing color
- `currentDrawMode`: SOLID, DOTTED, or FILLED
- `currentShapeMode`: Selected shape type
- `currentThick`: Line thickness (1-11)

### 4. Shape Hierarchy

**Base Interface**: `Drawable`
- Location: `com.iti.paintbrush.interfaces`
- Method: `void draw(Graphics g)`

**Abstract Base Class**: `Shape` implements `Drawable`, `Serializable`
- **Location**: `com.iti.paintbrush.shapes`
- **Common Properties**:
  - `x1, y1`: Starting coordinates
  - `x2, y2`: Ending coordinates
  - `color`: Shape color
  - `thick`: Line thickness
  - `drawMode`: Drawing mode (solid, dotted, filled)

**Concrete Shape Classes**:

1. **Line**: Draws straight lines
   - Supports solid and dotted modes
   - Uses `drawLine()` for solid, custom dotted implementation

2. **Rectangle**: Draws rectangles
   - Supports solid, dotted, and filled modes
   - Uses `drawRect()` and `fillRect()`

3. **Square**: Draws perfect squares
   - Calculates equal width/height from drag distance
   - Supports all draw modes

4. **Oval**: Draws ellipses
   - Uses `drawOval()` and `fillOval()`
   - Supports all draw modes

5. **Circle**: Draws perfect circles
   - Calculates radius from drag distance
   - Supports all draw modes

6. **FreeHand**: Freehand drawing (pencil tool)
   - Stores multiple points as an ArrayList
   - Connects points with lines
   - Supports solid and dotted drawing

7. **Eraser**: Erases drawn content
   - Draws white shapes over existing content
   - Works like FreeHand but with white color

---

## Features

### Drawing Tools
1. **Pencil (Free Hand)**: Draw freeform lines by dragging
2. **Line**: Draw straight lines
3. **Rectangle**: Draw rectangles
4. **Square**: Draw perfect squares
5. **Oval**: Draw ellipses
6. **Circle**: Draw perfect circles
7. **Eraser**: Erase parts of the drawing

### Drawing Modes (Mutually Exclusive Checkboxes)
1. **Solid**: Standard solid lines/outlines (default)
2. **Dotted**: Dashed/dotted line style
3. **Filled**: Fill shapes with solid color

### Color Palette
- **8 Colors Available**:
  - Black (default)
  - Gray
  - White
  - Red
  - Green
  - Yellow
  - Blue
  - Pink
- Visual feedback with cyan border on selected color

### Thickness Control
- **Slider Range**: 1 to 11 pixels
- **Default**: 2 pixels
- Applies to all shapes and lines

### Action Buttons
1. **Undo**: Remove the last drawn shape
2. **Redo**: Restore the last undone shape
3. **Clear**: Remove all shapes but keep background image
4. **Reset**: Complete reset (removes shapes and background)

### File Operations

**Menu Bar → File**:
1. **Save Project**: Serialize and save drawing to file
   - Saves all shapes as Java objects
   - File extension: `.paintbrush` or custom

2. **Load Project**: Load a previously saved project
   - Deserializes shapes from file
   - Restores entire drawing session

3. **Export to JPG**: Export drawing as image
   - Saves current canvas as JPG image
   - Resolution matches canvas size

4. **Import Background**: Add background image
   - Supports JPG, PNG, GIF
   - Image is stretched to fit canvas

---

## Design Patterns

### 1. Factory Pattern
**Class**: `ShapeFactory`
- **Location**: `com.iti.paintbrush.utils`
- **Purpose**: Centralized shape object creation
- **Method**: `createShape(ShapeMode mode, int x, int y, Color color, int thick, DrawMode drawMode)`
- **Benefits**:
  - Encapsulates object creation logic
  - Easy to add new shapes
  - Reduces coupling between UI and shape classes

### 2. Strategy Pattern
**Enumerations**:
- `DrawMode`: SOLID, DOTTED, FILLED
- `ShapeMode`: FREE_HAND, LINE, RECTANGLE, SQUARE, OVAL, CIRCLE, ERASER
- **Purpose**: Encapsulate drawing algorithms and behaviors
- Allows runtime switching of drawing strategies

### 3. Singleton-like Pattern
- `DrawingPanel` maintains single instance via Mainframe
- Ensures consistent state management

### 4. Observer Pattern (Implicit)
- Mouse event listeners observe user interactions
- Action listeners respond to button clicks
- Item listeners monitor checkbox state changes

---

## Package Structure

```
com.iti.paintbrush
├── main
│   └── PaintBrushApp.java          # Application entry point
├── ui
│   ├── Mainframe.java               # Main window with toolbar and menu
│   └── DrawingPanel.java            # Canvas for drawing
├── shapes
│   ├── Shape.java                   # Abstract base class
│   ├── Line.java
│   ├── Rectangle.java
│   ├── Square.java
│   ├── Oval.java
│   ├── Circle.java
│   ├── FreeHand.java
│   └── Eraser.java
├── enums
│   ├── DrawMode.java                # SOLID, DOTTED, FILLED
│   └── ShapeMode.java               # Shape type enumerations
├── interfaces
│   └── Drawable.java                # Contract for drawable objects
└── utils
    ├── ShapeFactory.java            # Factory for shape creation
    └── FileUtils.java               # File I/O operations
```

---

## User Interface

### Layout Structure
```
┌─────────────────────────────────────────────────┐
│  File Menu                                      │
├─────────────────────────────────────────────────┤
│  Actions: [Undo] [Redo] [Clear] [Reset]        │
├─────────────────────────────────────────────────┤
│  Colors: [■][■][■][■][■][■][■][■]              │
│  Shapes: [✎][╲][▭][□][⬭][○][⌧]                │
│  Mode: ☑Solid ☐Dotted ☐Filled                  │
│  Thickness: [────●─────]                        │
├─────────────────────────────────────────────────┤
│                                                 │
│              Drawing Canvas                     │
│                                                 │
│                                                 │
└─────────────────────────────────────────────────┘
```

### UI Components

**Toolbar Container**:
- **Layout**: BoxLayout (vertical)
- **Background**: Light gray
- **Contains**: Two rows (actions row + tools row)

**Color Buttons**:
- **Type**: JToggleButton with ButtonGroup
- **Size**: 30x30 pixels
- **Visual Feedback**: Cyan border (4px) on selection, gray border (1px) otherwise
- **Selection**: Mutually exclusive via ButtonGroup

**Shape Buttons**:
- **Type**: JToggleButton with Unicode symbols
- **Font**: Segoe UI Symbol (20pt)
- **Tooltips**: Descriptive names on hover
- **Selection**: Mutually exclusive via ButtonGroup

**Draw Mode Checkboxes**:
- **Type**: JCheckBox (3 checkboxes)
- **Options**: Solid, Dotted, Filled
- **Behavior**: Mutually exclusive via custom ItemListeners
- **Default**: Solid selected

**Thickness Slider**:
- **Type**: JSlider
- **Range**: 1-11
- **Default**: 2
- **Size**: 100x30 pixels
- **Live Update**: Changes apply immediately

---

## File Operations

### Save Project
**Functionality**: Serializes all shapes to disk
- **Method**: `FileUtils.saveProject(File file, ArrayList<Drawable> shapes)`
- **Format**: Binary serialization (Java ObjectOutputStream)
- **What's Saved**: All shape objects with their properties
- **File Dialog**: Standard JFileChooser save dialog

### Load Project
**Functionality**: Deserializes shapes from disk
- **Method**: `FileUtils.loadProject(File file)`
- **Returns**: `ArrayList<Drawable>`
- **Side Effects**: Clears current drawing and redo history
- **File Dialog**: Standard JFileChooser open dialog

### Export Image
**Functionality**: Exports canvas as JPG image
- **Method**: `FileUtils.exportImage(File file, JPanel panel)`
- **Process**:
  1. Creates BufferedImage of panel size
  2. Renders panel content to image
  3. Writes as JPG file
- **Resolution**: Matches current canvas dimensions (1350x600 default)

### Import Background
**Functionality**: Loads image as canvas background
- **Method**: `ImageIO.read(File file)`
- **Supported Formats**: JPG, PNG, GIF
- **Behavior**: Image is scaled to fit canvas
- **Persistence**: Background is drawn first in paintComponent()

---

## How It Works

### Application Startup Flow
1. **Main Method** → `PaintBrushApp.main()`
2. **Create Frame** → `new Mainframe()`
3. **Initialize Components**:
   - Create DrawingPanel
   - Build toolbar with buttons and controls
   - Build menu bar
4. **Layout Components**:
   - Menu bar at top
   - Toolbar at north
   - Drawing panel at center
5. **Display Window** → `setVisible(true)`

### Drawing Workflow

#### 1. Mouse Pressed
```
User clicks → mousePressed() event
↓
Check currentShapeMode
↓
If FREE_HAND or ERASER:
  - Create shape immediately
  - Add first point
  - Add to shapes list
Else:
  - Use ShapeFactory to create shape
  - Set starting coordinates (x1, y1)
  - Keep as currentShape (preview mode)
```

#### 2. Mouse Dragged
```
User drags → mouseDragged() event
↓
If FREE_HAND or ERASER:
  - Add new point to shape
Else:
  - Update end coordinates (x2, y2)
↓
repaint() → triggers paintComponent()
```

#### 3. Mouse Released
```
User releases → mouseReleased() event
↓
If FREE_HAND or ERASER:
  - Add final point
Else:
  - Set final coordinates
  - Add shape to shapes list
↓
Set currentShape = null
↓
repaint()
```

### Rendering Pipeline

**paintComponent(Graphics g)** executes in order:
1. **Clear Background**: `super.paintComponent(g)` - paints white background
2. **Draw Background Image**: If exists, stretch to canvas size
3. **Draw History**: Loop through all shapes in `shapes` list and call `draw(g)`
4. **Draw Preview**: If `currentShape` exists (during drag), draw it

### Undo/Redo Mechanism

**Undo**:
```
User clicks Undo
↓
If shapes list not empty:
  - Remove last shape from shapes
  - Add it to removedShapes
Else if backupShapes exists:
  - Restore backupShapes to shapes
  - Clear backupShapes
↓
repaint()
```

**Redo**:
```
User clicks Redo
↓
If removedShapes not empty:
  - Remove last shape from removedShapes
  - Add it back to shapes
↓
repaint()
```

### Clear vs Reset

**Clear**:
- Backs up current shapes to backupShapes
- Clears shapes list
- Clears redo history
- **Keeps** background image

**Reset**:
- Backs up shapes and background image
- Clears everything (shapes, redo, background)
- Complete fresh start
- Can undo once to restore backup

### State Management

**Color Selection**:
```
User clicks color button
↓
Button's ActionListener fires
↓
drawingPanel.setCurrentColor(color)
↓
Update all buttons' borders (cyan for selected, gray for others)
↓
Future shapes use new color
```

**Shape Selection**:
```
User clicks shape button
↓
Button's ActionListener fires
↓
drawingPanel.setCurrentShapeMode(mode)
↓
ButtonGroup handles mutual exclusivity
↓
Next mouse press creates selected shape type
```

**Draw Mode Selection**:
```
User checks a draw mode checkbox
↓
ItemListener fires
↓
Uncheck other two checkboxes
↓
drawingPanel.setDrawMode(mode)
↓
Future shapes use new draw mode
```

**Thickness Selection**:
```
User moves thickness slider
↓
ChangeListener fires
↓
drawingPanel.setCurrentThick(value)
↓
Future shapes use new thickness
```

---

## Key Implementation Details

### Mutually Exclusive Checkboxes
```java
// Custom mutual exclusivity without ButtonGroup
solidCheckbox.addItemListener(e -> {
    if (solidCheckbox.isSelected()) {
        dottedCheckbox.setSelected(false);
        filledCheckbox.setSelected(false);
        drawingPanel.setDrawMode(DrawMode.SOLID);
    }
});
```
- Each checkbox manually unchecks the others when selected
- Allows checkbox UI while maintaining radio button behavior
- More intuitive for users than toggle buttons

### Dotted Line Implementation
```java
// Custom dotted line drawing
if (drawMode == DrawMode.DOTTED) {
    float[] dash = {10, 5};  // 10px line, 5px gap
    BasicStroke dashedStroke = new BasicStroke(
        thick, 
        BasicStroke.CAP_BUTT, 
        BasicStroke.JOIN_MITER,
        10.0f, 
        dash, 
        0.0f
    );
    ((Graphics2D) g).setStroke(dashedStroke);
}
```

### Shape Factory Usage
```java
// Centralized shape creation
currentShape = ShapeFactory.createShape(
    currentShapeMode,  // What shape to create
    e.getX(),          // Starting X
    e.getY(),          // Starting Y
    currentColor,      // Color
    currentThick,      // Line thickness
    currentDrawMode    // Solid/Dotted/Filled
);
```

---

## Future Enhancement Possibilities

1. **More Shapes**: Triangle, polygon, star, arrow
2. **Text Tool**: Add text annotations
3. **Layers**: Support multiple drawing layers
4. **Zoom & Pan**: Navigate large canvases
5. **Color Picker**: Custom color selection
6. **Gradient Fill**: Gradient fill options
7. **Transparency**: Alpha channel support
8. **Selection Tool**: Select, move, and resize shapes
9. **Copy/Paste**: Clipboard operations
10. **Keyboard Shortcuts**: Ctrl+Z, Ctrl+Y, etc.

---

## Technical Requirements

- **Java Version**: Java 17 or higher
- **Build Tool**: Maven/Gradle (optional)
- **Dependencies**: Java SE only (no external libraries)
- **Operating System**: Cross-platform (Windows, macOS, Linux)

---

## Project Metadata

- **Developer**: ITI Student
- **Organization**: ITI (Information Technology Institute)
- **Framework**: Java Swing
- **License**: Educational/Academic Use
- **Version**: 1.0

---

## Summary

The Paint Brush application is a well-structured, feature-rich drawing application that demonstrates solid software engineering principles. It uses proven design patterns (Factory, Strategy), maintains clean separation of concerns, and provides an intuitive user interface. The codebase is organized into logical packages, making it maintainable and extensible for future enhancements.
