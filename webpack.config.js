var webpack = require('webpack');
var path = require('path');

const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');

var config = {
  entry: {
      app: './client/app/index.js',
      vendor: ['react', 'react-dom']
  },
  output: {
    path: path.resolve('dist'),
    filename: 'bundle.js'
  },
  module : {
    rules: [
      {
        test: /\.scss$/,
        use: ExtractTextPlugin.extract({
          fallback: 'style-loader',
          //resolve-url-loader may be chained before sass-loader if necessary
          use: ['css-loader', 'sass-loader']
        })
      },
      {
        test : /\.jsx?/,
        exclude: /node_modules/,
        loader: 'babel-loader',
          query: {
              presets: ["react", "es2015", "stage-2"]
          }
      }
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({ template: './client/index.html', filename: 'index.html',inject: 'body' }),
    new webpack.optimize.CommonsChunkPlugin({ name: 'vendor', filename: 'vendor.bundle.js' }),
    new ExtractTextPlugin('css/style.css')
  ]
};

module.exports = config;