<template>
  <div style="padding: 20px">
    <div>
      <md-content>Background</md-content>
      <md-content class="md-primary">Primary</md-content>
      <md-content class="md-accent">Accent</md-content>
    </div>
    <div>
      <md-datepicker v-model="selectedDate" />
      <md-field>
        <label for="movie">First day of a week</label>
        <md-select v-model="firstDayOfAWeek">
          <md-option value="0">Sunday</md-option>
          <md-option value="1">Monday</md-option>
        </md-select>
        <span class="md-helper-text">This config is global.</span>
      </md-field>
    </div>
    <div>
      <md-dialog :md-active.sync="showDialog">
        <md-dialog-title>Preferences</md-dialog-title>

        <md-tabs md-dynamic-height>
          <md-tab md-label="General">
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
          </md-tab>

          <md-tab md-label="Activity">
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
          </md-tab>

          <md-tab md-label="Account">
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Ullam mollitia dolorum dolores quae commodi impedit possimus qui, atque at voluptates cupiditate. Neque quae culpa suscipit praesentium inventore ducimus ipsa aut.</p>
          </md-tab>
        </md-tabs>

        <md-dialog-actions>
          <md-button class="md-primary" @click="showDialog = false">Close</md-button>
          <md-button class="md-primary" @click="showDialog = false">Save</md-button>
        </md-dialog-actions>
      </md-dialog>

      <md-button class="md-primary md-raised" @click="showDialog = true">Show Dialog</md-button>
    </div>
    <div>
      <form novalidate class="md-layout" @submit.prevent="validateUser">
        <md-card class="md-layout-item md-size-50 md-small-size-100">
          <md-card-header>
            <div class="md-title">Users</div>
          </md-card-header>

          <md-card-content>
            <div class="md-layout md-gutter">
              <div class="md-layout-item md-small-size-100">
                <md-field :class="getValidationClass('firstName')">
                  <label for="first-name">First Name</label>
                  <md-input name="first-name" id="first-name" autocomplete="given-name" v-model="form.firstName" :disabled="sending" />
                  <span class="md-error" v-if="!$v.form.firstName.required">The first name is required</span>
                  <span class="md-error" v-else-if="!$v.form.firstName.minlength">Invalid first name</span>
                </md-field>
              </div>

              <div class="md-layout-item md-small-size-100">
                <md-field :class="getValidationClass('lastName')">
                  <label for="last-name">Last Name</label>
                  <md-input name="last-name" id="last-name" autocomplete="family-name" v-model="form.lastName" :disabled="sending" />
                  <span class="md-error" v-if="!$v.form.lastName.required">The last name is required</span>
                  <span class="md-error" v-else-if="!$v.form.lastName.minlength">Invalid last name</span>
                </md-field>
              </div>
            </div>

            <div class="md-layout md-gutter">
              <div class="md-layout-item md-small-size-100">
                <md-field :class="getValidationClass('gender')">
                  <label for="gender">Gender</label>
                  <md-select name="gender" id="gender" v-model="form.gender" md-dense :disabled="sending">
                    <md-option></md-option>
                    <md-option value="M">M</md-option>
                    <md-option value="F">F</md-option>
                  </md-select>
                  <span class="md-error">The gender is required</span>
                </md-field>
              </div>

              <div class="md-layout-item md-small-size-100">
                <md-field :class="getValidationClass('age')">
                  <label for="age">Age</label>
                  <md-input type="number" id="age" name="age" autocomplete="age" v-model="form.age" :disabled="sending" />
                  <span class="md-error" v-if="!$v.form.age.required">The age is required</span>
                  <span class="md-error" v-else-if="!$v.form.age.maxlength">Invalid age</span>
                </md-field>
              </div>
            </div>

            <md-field :class="getValidationClass('email')">
              <label for="email">Email</label>
              <md-input type="email" name="email" id="email" autocomplete="email" v-model="form.email" :disabled="sending" />
              <span class="md-error" v-if="!$v.form.email.required">The email is required</span>
              <span class="md-error" v-else-if="!$v.form.email.email">Invalid email</span>
            </md-field>
          </md-card-content>

          <md-progress-bar md-mode="indeterminate" v-if="sending" />

          <md-card-actions>
            <md-button type="submit" class="md-primary" :disabled="sending">Create user</md-button>
          </md-card-actions>
        </md-card>

        <md-snackbar :md-active.sync="userSaved">The user {{ lastUser }} was saved with success!</md-snackbar>
      </form>
    </div>
    <form novalidate @submit.stop.prevent="showSnackbar = true">
      <div>
        <div for="snackbar-center" class="md-body-2">Position</div>
        <md-radio v-model="position" id="snackbar-center" name="snackbar-horizontal" value="center">Centered</md-radio>
        <md-radio v-model="position" id="snackbar-left" name="snackbar-horizontal" value="left">Left</md-radio>
      </div>

      <div>
        <div class="md-body-2">Duration</div>

        <md-switch v-model="isInfinity">Infinite</md-switch>

        <md-field>
          <label for="snackbar-duration">Miliseconds</label>
          <md-input type="number" id="snackbar-duration" v-model.number="duration" :disabled="isInfinity"></md-input>
        </md-field>
      </div>

      <md-button type="submit" class="md-primary md-raised">Open Snackbar</md-button>

      <md-snackbar :md-position="position" :md-duration="isInfinity ? Infinity : duration" :md-active.sync="showSnackbar" md-persistent>
        <span>Connection timeout. Showing limited messages!</span>
        <md-button class="md-primary" @click="showSnackbar = false">Retry</md-button>
      </md-snackbar>
    </form>
  </div>
</template>

<script>
import { validationMixin } from 'vuelidate'
import {
  required,
  email,
  minLength,
  maxLength
} from 'vuelidate/lib/validators'

export default {
  mixins: [validationMixin],
  data: () => ({
    selectedDate: null,
    showDialog: false,
    form: {
      firstName: null,
      lastName: null,
      gender: null,
      age: null,
      email: null
    },
    userSaved: false,
    sending: false,
    lastUser: null,
    showSnackbar: false,
    position: 'center',
    duration: 4000,
    isInfinity: false
  }),
  computed: {
    firstDayOfAWeek: {
      get () {
        return this.$material.locale.firstDayOfAWeek
      },
      set (val) {
        this.$material.locale.firstDayOfAWeek = val
      }
    }
  },
  validations: {
    form: {
      firstName: {
        required,
        minLength: minLength(3)
      },
      lastName: {
        required,
        minLength: minLength(3)
      },
      age: {
        required,
        maxLength: maxLength(3)
      },
      gender: {
        required
      },
      email: {
        required,
        email
      }
    }
  },
  methods: {
    getValidationClass (fieldName) {
      const field = this.$v.form[fieldName]

      if (field) {
        return {
          'md-invalid': field.$invalid && field.$dirty
        }
      }
    },
    clearForm () {
      this.$v.$reset()
      this.form.firstName = null
      this.form.lastName = null
      this.form.age = null
      this.form.gender = null
      this.form.email = null
    },
    saveUser () {
      this.sending = true

      // Instead of this timeout, here you can call your API
      window.setTimeout(() => {
        this.lastUser = `${this.form.firstName} ${this.form.lastName}`
        this.userSaved = true
        this.sending = false
        this.clearForm()
      }, 1500)
    },
    validateUser () {
      this.$v.$touch()

      if (!this.$v.$invalid) {
        this.saveUser()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .md-content {
    width: 200px;
    height: 200px;
    display: inline-flex;
    justify-content: center;
    align-items: center;
  }
  .md-dialog {
    max-width: 768px;
  }
  .md-progress-bar {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
  }
</style>
