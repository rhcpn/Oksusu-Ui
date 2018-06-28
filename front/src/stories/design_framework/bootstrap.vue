<template>
  <b-container>
    <b-col>
      <b-alert show>Default Alert</b-alert>

      <b-alert variant="success" show>Success Alert</b-alert>

      <b-alert variant="danger"
               dismissible
               :show="showDismissibleAlert"
               @dismissed="showDismissibleAlert=false">
        Dismissible Alert!
      </b-alert>

      <b-alert :show="dismissCountDown"
               dismissible
               variant="warning"
               @dismissed="dismissCountDown=0"
               @dismiss-count-down="countDownChanged">
        <p>This alert will dismiss after {{dismissCountDown}} seconds...</p>
        <b-progress variant="warning"
                    :max="dismissSecs"
                    :value="dismissCountDown"
                    height="4px">
        </b-progress>
      </b-alert>

      <b-btn @click="showAlert" variant="info" class="m-1">
        Show alert with count-down timer
      </b-btn>
      <b-btn @click="showDismissibleAlert=true" variant="info" class="m-1">
        Show dismissible alert ({{showDismissibleAlert?'visible':'hidden'}})
      </b-btn>
    </b-col>

    <b-col>
      <b-table striped hover :items="items"></b-table>
    </b-col>

    <b-col>
      <!-- Using modifiers -->
      <b-btn v-b-modal.myModal>Show Modal</b-btn>

      <!-- Using value -->
      <b-btn v-b-modal="'myModal'">Show Modal</b-btn>

      <!-- the modal -->
      <b-modal id="myModal">
        Hello From My Modal!
      </b-modal>
    </b-col>

    <b-col>
      <b-form @submit="onSubmit" @reset="onReset" v-if="show">
        <b-form-group id="exampleInputGroup1"
                      label="Email address:"
                      label-for="exampleInput1"
                      description="We'll never share your email with anyone else.">
          <b-form-input id="exampleInput1"
                        type="email"
                        v-model="form.email"
                        required
                        placeholder="Enter email">
          </b-form-input>
        </b-form-group>
        <b-form-group id="exampleInputGroup2"
                      label="Your Name:"
                      label-for="exampleInput2">
          <b-form-input id="exampleInput2"
                        type="text"
                        v-model="form.name"
                        required
                        placeholder="Enter name">
          </b-form-input>
        </b-form-group>
        <b-form-group id="exampleInputGroup3"
                      label="Food:"
                      label-for="exampleInput3">
          <b-form-select id="exampleInput3"
                         :options="foods"
                         required
                         v-model="form.food">
          </b-form-select>
        </b-form-group>
        <b-form-group id="exampleGroup4">
          <b-form-checkbox-group v-model="form.checked" id="exampleChecks">
            <b-form-checkbox value="me">Check me out</b-form-checkbox>
            <b-form-checkbox value="that">Check that out</b-form-checkbox>
          </b-form-checkbox-group>
        </b-form-group>
        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" variant="danger">Reset</b-button>
      </b-form>
    </b-col>

    <b-col>
      <b-container fluid class="p-4 bg-dark">
        <b-row>
          <b-col>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=54" alt="Thumbnail" />
          </b-col>
          <b-col>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=58" alt="Thumbnail" />
          </b-col>
          <b-col>
            <b-img thumbnail fluid src="https://picsum.photos/250/250/?image=59" alt="Thumbnail" />
          </b-col>
        </b-row>
      </b-container>
    </b-col>

  </b-container>
</template>

<script>
const items = [
  { isActive: true, age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
  { isActive: false, age: 21, first_name: 'Larsen', last_name: 'Shaw' },
  { isActive: false, age: 89, first_name: 'Geneva', last_name: 'Wilson' },
  { isActive: true, age: 38, first_name: 'Jami', last_name: 'Carney' }
]
export default {
  data () {
    return {
      dismissSecs: 10,
      dismissCountDown: 0,
      showDismissibleAlert: false,
      items: items,
      form: {
        email: '',
        name: '',
        food: null,
        checked: []
      },
      foods: [
        { text: 'Select One', value: null },
        'Carrots', 'Beans', 'Tomatoes', 'Corn'
      ],
      show: true
    }
  },
  methods: {
    countDownChanged (dismissCountDown) {
      this.dismissCountDown = dismissCountDown
    },
    showAlert () {
      this.dismissCountDown = this.dismissSecs
    },
    onSubmit (evt) {
      evt.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset (evt) {
      evt.preventDefault()
      /* Reset our form values */
      this.form.email = ''
      this.form.name = ''
      this.form.food = null
      this.form.checked = []
      /* Trick to reset/clear native browser form validation state */
      this.show = false
      this.$nextTick(() => { this.show = true })
    }
  }
}
</script>

<style scoped>

</style>
