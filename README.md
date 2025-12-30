### Day 1: The Setup & UI (Completed)

**Goal:** Get the project running and create the main window.

#### 1. What We Built

**Git Repo:** Set up the repository so we can work together.

**PaintBrushApp.java:** The entry point (Main Class) that launches the app using a thread.

**MainFrame.java:** The main window. We used a BorderLayout:

**North:** The Toolbar (Buttons for Rect, Oval, Red, Green, etc.).

**Center:** The White Drawing Area (initially just a blank JPanel).

### 2. Key Takeaway

We established the "skeleton" of the app. The buttons didn't work yet, and the white area couldn't draw anything, but the window opened successfully.

---

### Day 2: Drawing Panel (Engine)

We replaced the dumb white panel with a custom DrawingPanel.java.

- **- Mouse Listeners:** We added logic to detect Clicked (Start), Dragged (Moving), and Released (Finish).

- **- The List:** We use ArrayList<Drawable>. This is the history of everything drawn on screen.

- **- The Math:** We added logic to handle "Backwards Dragging" (dragging from bottom-right to top-left) using Math.min() and Math.abs().

#### - Current Status

You can click and drag to draw a Red Rectangle. The shape stays on the screen after you release the mouse. The dragging animation works smoothly

---

### Day 3: Architecture, Shapes & Tools (The "Bonus" Update)

We moved from a simple prototype to a professionally architected application.

**- Refactoring & Architecture (SOLID Principles)**

- **ShapeFactory:** Removed the messy `switch` case from the UI. The factory now handles creating all shapes.
- **FileUtils:** Moved all Save/Load logic to a separate utility class to keep the UI clean.
- **Enums:** Replaced magic strings ("Rectangle") with proper Enums (`ShapeMode.RECTANGLE`).

**- New Shapes**

- **Circle & Square:** Implemented special logic (LSP Principle) so they calculate their own size to remain perfect, regardless of mouse drag.
- **Line & Oval:** Added standard implementation.

**- History & Files**

- **Undo / Redo:** Implemented using two stacks logic.
- **Clear / Reset:** Added logic to wipe the screen (with safety backup for Undo).
- **Save/Load Project:** We can now save the editable vector shapes to a file (`.ser`) and load them back later.
- **Export/Import Image:** Added ability to export the drawing as a JPEG and import images as a background to draw over.

---

### Day 4: Architecture, UI and Shapes  (The "Bonus" Update)


**- Arhcitecture**
- **ShapeFactory:** now contains Eraser and FreeHand

**- UI**
- **Colors** Now the colors are grouped in a button Group, only one color is highlighted at atime.
- **Shaoes** added new shapes (Pencil, and Eraser), added separator
- **Actions** added a label before the actions


**- New Shapes**

- **Draw Line:** - included an ArrayList of integer lists that store points. - lines are drawn between the points - ovals are also drawn (under consideration to remove, thickness is static)

- **Eraser:** included an ArrayList of integer lists that store points. - uses white color statically - lines are drawn between the points (defualt thickness because we are using draw not draw2d) - ovals are also drawn (under consideration to remove, thickness is static)

---

### Day 5: Architecture, UI and Shapes  (The "Bonus" Update)

**- Arhcitecture**
- **Colors:** now contains Black and white, new buttons actions, 
- **DrawMode:** added functionality for solid, dotted, filled
- **Draw:** now using g2d not graphics g
- **Thickness:** now allowing the user to change the line thickness

**- UI**
- **Colors** changed color buttons, Now the colors buttons have an outline when pressed instead of the regular color
- **Shapes** shapes now using unicode symbols
- **ThicknesS** added a slider for  thickness
---

### TODO: 
- **[ ] Testing:** Try to break the app and fix any small bugs found.

