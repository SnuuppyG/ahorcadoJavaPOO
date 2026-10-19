# 🎯 Juego del Ahorcado · Java Swing

Juego clásico del ahorcado desarrollado en **Java** con interfaz gráfica en **Swing**, aplicando los principios de **Programación Orientada a Objetos** (encapsulación, herencia y polimorfismo).

---

## 📖 Descripción

Adivina la palabra secreta antes de agotar los **7 intentos**. Cada error dibuja una parte del personaje en la horca:

1. 🤕 Cabeza
2. 🧍 Torso
3. 💪 Brazo derecho
4. 💪 Brazo izquierdo
5. 🦵 Pierna derecha
6. 🦵 Pierna izquierda
7. 🪢 Cuerda final

## ✨ Características

- 📚 Banco de **200 palabras** distribuidas en **5 categorías**: animales, países, deportes, objetos y comidas.
- 💡 **3 pistas** por partida:
  - **Categoría:** te dice a qué grupo pertenece la palabra.
  - **Letra revelada:** descubre una letra aleatoria.
  - **Descripción:** una pista escrita relacionada con la palabra.
- 🔤 Teclado virtual con registro de letras correctas e incorrectas.
- 🔄 Opción de nueva partida en cualquier momento.

## 🛠️ Tecnologías

- ☕ Java 11 o superior
- 🎨 Swing (`javax.swing`)
- 📂 `ArrayList` para el manejo de palabras y categorías

## 🧱 Estructura del proyecto

```
.
├── recursos/
│   └── palabras.txt        # Banco de palabras (formato: categoría;PALABRA;pista)
├── src/
│   └── ahorcado/
│       ├── app/            # Punto de entrada (Main)
│       ├── logica/         # Lógica del juego y pistas (herencia y polimorfismo)
│       ├── modelo/         # Banco de palabras y fichas
│       └── ui/             # Interfaz gráfica (ventana y dibujo del ahorcado)
└── .gitignore
```

## 🚀 Cómo ejecutarlo

1. Clona el repositorio:
```bash
   git clone https://github.com/SnuppyG/ahorcadoJavaPOO.git
```
2. Ábrelo en **IntelliJ IDEA** (o tu IDE favorito).
3. Asegúrate de que la carpeta `recursos/` esté en la raíz del proyecto.
4. Ejecuta `src/ahorcado/app/Main.java`.

## 📋 Formato del banco de palabras

Cada línea de `recursos/palabras.txt` tiene el formato:

Las líneas que empiezan con `#` se ignoran como comentarios.

## 👨‍💻 Autor

**Felipe Garzón** ([@SnuppyG](https://github.com/SnuppyG))

Proyecto desarrollado para el curso de Programación Orientada a Objetos (POO Virtual).
