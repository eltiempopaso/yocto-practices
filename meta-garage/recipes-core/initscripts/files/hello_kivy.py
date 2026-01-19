#!/usr/bin/env python3

import os

# Forzar backend SDL2 + KMS/DRM
os.environ["KIVY_WINDOW"] = "sdl2"
os.environ["SDL_VIDEODRIVER"] = "kmsdrm"

# Opcional pero recomendable en embedded
os.environ["KIVY_NO_ARGS"] = "1"
os.environ["KIVY_LOG_LEVEL"] = "info"

from kivy.app import App
from kivy.uix.label import Label
from kivy.core.window import Window


class HelloApp(App):
    def build(self):
        Window.clearcolor = (0.1, 0.1, 0.1, 1)
        return Label(
            text="Hello Kivy\n(KMS/DRM + SDL2)",
            font_size="40sp",
            halign="center",
            valign="middle"
        )


if __name__ == "__main__":
    HelloApp().run()
