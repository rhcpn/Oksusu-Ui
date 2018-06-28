/* eslint-disable react/react-in-jsx-scope */

import {storiesOf} from '@storybook/vue'
import {action} from '@storybook/addon-actions'
import {linkTo} from '@storybook/addon-links'
import Vue from 'vue'
import Datepicker from 'vuejs-datepicker'
import * as lang from 'vuejs-datepicker/src/locale'

import Notifications from 'vue-notification'
import velocity from 'velocity-animate'

import cm from '../resource/config/messages'

import TreeTest from './tree/TreeTest.vue'
import MyButton from './MyButton.vue'
import Welcome from './Welcome.vue'

import WelcomeTwo from './WelcomeTwo.vue'
import ListTest from './ListTest.vue'
import FormTest from './FormTest.vue'
import VueBoard from './VueBoard.vue'
import DatepickerTest from './datepicker/DatepickerTest.vue'
import AgGrid from './AgGrid.vue'
import ResourceTest from './ResourceTest.vue'

import HighchartTest from './HighchartTest.vue'
import AreaChart from './highcharts/AreaChart.vue'
import BarChart from './highcharts/BarChart.vue'
import BubbleChart from './highcharts/BubbleChart.vue'
import Combinations from './highcharts/Combinations.vue'
import DynamicChart from './highcharts/DynamicChart.vue'
import Gauges from './highcharts/Gauges.vue'
import H3dChart from './highcharts/H3dChart.vue'
import HeatTreeMaps from './highcharts/HeatTreeMaps.vue'
import LineChart from './highcharts/LineChart.vue'
import LoadModuleChart from './highcharts/LoadModuleChart.vue'
import PieChart from './highcharts/PieChart.vue'
import ResponsiveChart from './highcharts/ResponsiveChart.vue'
import ScatterPlotChart from './highcharts/ScatterPlotChart.vue'
import VueNotification from './VueNotification.vue'
import '../../node_modules/ag-grid/dist/styles/ag-grid.css'
import '../../node_modules/ag-grid/dist/styles/ag-theme-balham.css'

import BootstrapTest from './design_framework/bootstrap'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VueMaterialTest from './design_framework/vuematerial'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.css'
import 'vue-material/dist/theme/default.css'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import VuetifyTest from './design_framework/vuetify'

Vue.use(Vuetify)

Vue.use(BootstrapVue)
Vue.use(VueMaterial)

Vue.component('Datepicker', Datepicker)
Vue.prototype.lang = lang

Vue.use(Notifications, { velocity })

Vue.prototype.$message = cm

storiesOf('Welcome', module).add('to Storybook', () => ({
  components: {Welcome},
  template: '<welcome :showApp="action" />',
  methods: {action: linkTo('Button')}
}))
storiesOf('WelcomeTwo', module).add('to Storybook', () => ({
  components: {WelcomeTwo},
  template: '<welcome-two :showApp="action" />',
  methods: {action: linkTo('Button')}
}))

storiesOf('Button', module)
  .add('with text', () => ({
    components: {MyButton},
    template: '<my-button @click="action">Hello Button</my-button>',
    methods: {action: action('clicked')}
  }))
  .add('with Vue', () => ({
    components: {MyButton},
    template:
      '<my-button @click="action">With Vue</my-button>',
    methods: {action: action('clicked')}
  }))
  .add('with some emoji', () => ({
    components: {MyButton},
    template:
      '<my-button @click="action"><span role="img" aria-label="so cool">😀 😎 👍 💯</span></my-button>',
    methods: {action: action('clicked')}
  }))
// .add('with list', () => ({
//   components: {List},
//   template: '<list :showApp="action" />',
//   methods: {action: action('clicked')}
// }))

storiesOf('List', module).add('ListTest', () => ({
  components: {ListTest},
  template: '<list-test></list-test>'
}))

storiesOf('Form', module).add('FormTest', () => ({
  components: {FormTest},
  template: '<form-test></form-test>'
}))

storiesOf('Board', module).add('VueBoard', () => ({
  components: {VueBoard},
  template: '<vue-board></vue-board>'
}))

storiesOf('Chart', module)
  .add('HighchartTest', () => ({
    components: {HighchartTest},
    template: '<highchart-test></highchart-test>'
  }))
  .add('AreaChart', () => ({
    components: {AreaChart},
    template: '<area-chart></area-test>'
  }))
  .add('BarChart', () => ({
    components: {BarChart},
    template: '<bar-chart></bar-test>'
  }))
  .add('BubbleChart', () => ({
    components: {BubbleChart},
    template: '<bubble-chart></bubble-chart>'
  }))
  .add('Combinations', () => ({
    components: {Combinations},
    template: '<combinations></combinations>'
  }))
  .add('DynamicChart', () => ({
    components: {DynamicChart},
    template: '<dynamic-chart></dynamic-chart>'
  }))
  .add('Gauges', () => ({
    components: {Gauges},
    template: '<gauges></gauges>'
  }))
  .add('H3dChart', () => ({
    components: {H3dChart},
    template: '<h3d-chart></h3d-chart>'
  }))
  .add('HeatTreeMaps', () => ({
    components: {HeatTreeMaps},
    template: '<heat-tree-maps></heat-tree-maps>'
  }))
  .add('LineChart', () => ({
    components: {LineChart},
    template: '<line-chart></line-chart>'
  }))
  .add('LoadModuleChart', () => ({
    components: {LoadModuleChart},
    template: '<load-module-chart></load-module-chart>'
  }))
  .add('PieChart', () => ({
    components: {PieChart},
    template: '<pie-chart></pie-chart>'
  }))
  .add('ResponsiveChart', () => ({
    components: {ResponsiveChart},
    template: '<responsive-chart></responsive-chart>'
  }))
  .add('ScatterPlotChart', () => ({
    components: {ScatterPlotChart},
    template: '<scatter-plot-chart></scatter-plot-chart>'
  }))

storiesOf('TreeTest', module).add('TreeTest', () => ({
  components: { TreeTest },
  template: '<tree-test></tree-test>'
}))

storiesOf('DataGrid', module).add('ag-Grid', () => ({
  components: { AgGrid },
  template: '<ag-grid></ag-grid>'
}))

storiesOf('DatepickerTest', module).add('DatepickerTest', () => ({
  components: { DatepickerTest },
  template: '<datepicker-test></datepicker-test>'
}))

storiesOf('ResourceTest', module).add('ResourceTest', () => ({
  components: { ResourceTest },
  template: '<resource-test></resource-test>'
}))

storiesOf('Design framework', module)
  .add('BootstrapTest', () => ({
    components: { BootstrapTest },
    template: '<bootstrap-test></bootstrap-test>'
  }))
  .add('VueMaterialTest', () => ({
    components: { VueMaterialTest },
    template: '<vue-material-test></vue-material-test>'
  }))
  .add('VuetifyTest', () => ({
    components: { VuetifyTest },
    template: '<vuetify-test></vuetify-test>'
  }))

storiesOf('Notification', module).add('vue-notification', () => ({
  components: { VueNotification },
  template: '<vue-notification></vue-notification>'
}))

/* eslint-enable react/react-in-jsx-scope */
