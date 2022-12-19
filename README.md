
Node.js example for [shadow-cljs](https://github.com/thheller/shadow-cljs)
----

### Develop

Watch compile with with hot reloading:

```bash
yarn
yarn shadow-cljs watch app
```

Start program:

```bash
node target/main.js
```

### CALVA

1. `CMD + SHIFT + P`
2. Connecting to a running REPL server in the ...
3. Choose shadow-cljs
4. Use automatic port, or copy from shadow-cljs watch output
5. Choose `:node-repl`
6. `Ctrl + Enter` to run function inline.
7. Enjoy.

### REPL

Start a REPL connected to current running program, `app` for the `:build-id`:

```bash
yarn shadow-cljs cljs-repl app
```

### Build

```bash
shadow-cljs release app
```

Compiles to `target/main.js`.

You may find more configurations on http://doc.shadow-cljs.org/ .

### Steps

* add `shadow-cljs.edn` to config compilation
* compile ClojureScript
* run `node target/main.js` to start app and connect reload server

### License

MIT
