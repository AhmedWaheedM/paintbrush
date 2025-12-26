Day 1: The Setup & UI (Completed)
Goal: Get the project running and create the main window.

1. What We Built
Git Repo: Set up the repository so we can work together.

PaintBrushApp.java: The entry point (Main Class) that launches the app using a thread.

MainFrame.java: The main window. We used a BorderLayout:

North: The Toolbar (Buttons for Rect, Oval, Red, Green, etc.).

Center: The White Drawing Area (initially just a blank JPanel).

2. Key Takeaway
We established the "skeleton" of the app. The buttons didn't work yet, and the white area couldn't draw anything, but the window opened successfully.

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

Day 2: Drawing Panel (Engine)
We replaced the dumb white panel with a custom DrawingPanel.java.

- Mouse Listeners: We added logic to detect Clicked (Start), Dragged (Moving), and Released (Finish).

- The List: We use ArrayList<Drawable>. This is the history of everything drawn on screen.

- The Math: We added logic to handle "Backwards Dragging" (dragging from bottom-right to top-left) using Math.min() and Math.abs().

3. Current Status
You can click and drag to draw a Red Rectangle. The shape stays on the screen after you release the mouse. The dragging animation works smoothly

>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

Day 3: Preparation for Day 4 

- Prepared DrawingPanel.java for Redo/Undo 
- Added togglebuttons to toolbar 
- Added Save and Load buttons to the toolbar

TODO: (Dah el Fakro :P)
Buttons are not functional at this point, we need to wire them up (add logic to currentMode and currentShape @ DrawingPanel.java)
Add Oval and Line
Add Freehand and Eraser 
Add Dotted option and Fill option 
Bonus: Undo -> remove last shape from arraylist
Bonus: Redo -> re-intiate the original arraylist
Bonus: Clear All -> .clear() arraylist
Bonus: Add JPEG functionality (Save & Load) -> Using IO and then saving the panel as jpeg, and using io and setting the JPEG as the background
Bonus: el UI shaklo 3efesh 3ayz a7aseno ma3rfsh ezay lesa :P
