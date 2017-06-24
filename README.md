HTTP server to format Scala code for text editors (like vim)

[![Build Status](https://api.travis-ci.org/alopatindev/scalafmt-server.svg?branch=master)](https://travis-ci.org/alopatindev/scalafmt-server)

# Why
`scalafmt` starts slowly (as any JVM-based app) and [`nailgun`](http://scalameta.org/scalafmt/#Nailgun) is buggy and unmaintaned (?)

<p align="center"><img src="https://raw.githubusercontent.com/alopatindev/assets/master/weird-ng-behavior.png" width="838" height="340"></p>

That's sad

# Usage
Unpack and run the server
```sh
sbt universal:packageZipTarball
tar xzf target/universal/scalafmt-server-*.tgz
bash scalafmt-server-*/bin/scalafmt-server -Dhttp.address=localhost -Dhttp.port=8899 &
```

If you're using [vim-autoformat](https://github.com/Chiel92/vim-autoformat):
```viml
let g:formatdef_scalafmt = "'path/to/scalafmt-client.sh 8899'"
let g:formatters_scala = ['scalafmt']
au BufWrite *.scala :Autoformat
```

# License
Licensed under the terms of MIT (read LICENSE.txt for details)
