You're going to want to use [nvm](https://github.com/creationix/nvm), because Node versions change all the time.

On a Mac or Linux you can install it via:

```
curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.33.2/install.sh | bash
```

It will give you some instructions to use put it in your `.bashrc` or `.profile`:

```
export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && . "$NVM_DIR/nvm.sh" # This loads nvm
```

Then, you need to install `node`:

```
nvm install --lts node
```

Now, you can install the dependencies:

```
npm install
```

And run the app locally!

```
PORT=3000 npm run start
```

Modifications should be made to `server.js`, and then you need to stop and restart the app.
