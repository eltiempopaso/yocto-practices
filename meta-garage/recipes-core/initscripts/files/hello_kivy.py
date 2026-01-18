from kivy.app import App
from kivy.uix.label import Label
from kivy.config import Config

# Fullscreen y sin border
Config.set('graphics', 'fullscreen', 'auto')
Config.set('graphics', 'borderless', '1')

class HelloApp(App):
    def build(self):
        return Label(text="Hello World", font_size='48sp')

if __name__ == '__main__':
    HelloApp().run()

