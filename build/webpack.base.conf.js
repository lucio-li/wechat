/*! =======================================================
                      VERSION  1.1.0
 * ========================================================
 * webpack.base.config.js
 * ========================================================
 * 本文件为webpack基本配置文件
 * ======================================================== */
let path = require('path'),
    utils = require('./utils'),
    config = require('../config'),
    webpack = require("webpack"),
    vueLoaderConfig = require('./vue-loader.conf')

const ROOT_PATH = path.resolve(__dirname);
const APP_PATH = path.resolve(ROOT_PATH, 'src');

function resolve(dir) {
  return path.join(__dirname, '..', dir)
}

module.exports = {
  // 文件入口 entry
  entry: {
    // app入口配置
    app: './src/main.js'
  },
  // 输出文件夹路径以及可被分块输出路径和名字
  output: {
    // 输出文件路径
    path: config.build.assetsRoot,
    // 输出文件名字
    filename: '[name].js',
    // 公共路径输出配置
    publicPath: process.env.NODE_ENV === 'production' ?
      config.build.assetsPublicPath : config.dev.assetsPublicPath
  },
  plugins: [
    new webpack.optimize.CommonsChunkPlugin('common.js'),
    // 全局变量，无需每个模块引入
    new webpack.ProvidePlugin({
      jQuery: "jquery",
      $: "jquery"
    })
  ],
  resolve: {
    extensions: ['.js', '.vue', '.json'],
    alias: {
      'vue$': 'vue/dist/vue.esm.js',
      '@': resolve('src'),
      'assets': path.resolve(__dirname, '../src/assets'),
      'components': path.resolve(__dirname, '../src/components'),
      'jquery': "jquery/src/jquery"
    }
  },
  module: {
    rules: [{
      test: /\.vue$/,
      loader: 'vue-loader',
      // options: vueLoaderConfig
      options: {
        loaders: {
          scss: 'vue-style-loader!css-loader!sass-loader', // <style lang="scss">
          sass: 'vue-style-loader!css-loader!sass-loader?indentedSyntax' // <style lang="sass">
        }
      }
    },
    {
      test: /\.json$/,
      loader: 'json'
    },
    {
      test: /\.js$/,
      loader: 'babel-loader',
      include: [resolve('src'), resolve('test')]
    },
    {
      test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
      loader: 'url-loader',
      options: {
        limit: 10000,
        name: utils.assetsPath('img/[name].[hash:7].[ext]')
      }
    },
    {
      test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
      loader: 'url-loader',
      options: {
        limit: 10000,
        name: utils.assetsPath('fonts/[name].[hash:7].[ext]')
      }
    }
    ]
  }
}
