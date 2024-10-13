import tkinter as tk
from tkinter.filedialog import askopenfilename, asksaveasfilename
from tkinter import colorchooser


def open_file(window, text_edit):
    filepath = askopenfilename(filetypes=[("Text Files", "*.txt")])

    if not filepath:
        return

    text_edit.delete(1.0, tk.END)
    with open(filepath, "r") as f:
        content = f.read()
        text_edit.insert(tk.END, content)
    window.title(f"Open File: {filepath}")


def save_file(window, text_edit):
    filepath = asksaveasfilename(filetypes=[("Text Files", "*.txt")])

    if not filepath:
        return

    with open(filepath, 'w') as f:
        content = text_edit.get(1.0, tk.END)
        f.write(content)
    window.title(f"Save File: {filepath}")


def change_font_size(window, text_edit, delta):
    current_font = text_edit.cget("font")
    font_name, font_size = current_font.split()
    font_size = int(font_size) + delta
    new_font = (font_name, font_size)
    text_edit.config(font=new_font)


def change_text_color(window, text_edit):
    try:
        original_color = text_edit.index("sel.first")
        new_color = text_edit.index("sel.last")
    except tk.TclError:
        return

    color = colorchooser.askcolor()[1]
    if color:
        color_change = f"colored_{original_color}_{new_color}"
        text_edit.tag_add(color_change, original_color, new_color)
        text_edit.tag_config(color_change, foreground=color)


def main():
    window = tk.Tk()
    window.title("Text Editor")
    window.rowconfigure(0, minsize=400)
    window.columnconfigure(1, minsize=500)

    text_edit = tk.Text(window, font="Helvetica 18")
    text_edit.grid(row=0, column=1)

    frame = tk.Frame(window, relief=tk.RAISED, bd=2)
    save_button = tk.Button(frame, text="Save", command=lambda: save_file(window, text_edit))
    open_button = tk.Button(frame, text="Open", command=lambda: open_file(window, text_edit))
    enlarge_font_button = tk.Button(frame, text="Font++", command=lambda: change_font_size(window, text_edit, 2))
    shrink_font_button = tk.Button(frame, text="Font--", command=lambda: change_font_size(window, text_edit, -2))
    color_button = tk.Button(frame, text="Change Text Color", command=lambda: change_text_color(window, text_edit))

    save_button.grid(row=0, column=0, padx=5, pady=5, sticky="ew")
    open_button.grid(row=1, column=0, padx=5, pady=5, sticky="ew")
    enlarge_font_button.grid(row=2, column=0, padx=5, pady=5, sticky="ew")
    shrink_font_button.grid(row=3, column=0, padx=5, pady=5, sticky="ew")
    color_button.grid(row=4, column=0, padx=5, pady=5, sticky="ew")
    frame.grid(row=0, column=0, sticky="ns")

    window.bind("<Control-s>", lambda x: save_file(window, text_edit))
    window.bind("<Control-o>", lambda x: open_file(window, text_edit))

    window.mainloop()


main()
