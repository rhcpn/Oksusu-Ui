<template>
  <div>
    <form @submit.prevent="onsubmit">
      <div class="form-group">
        <label>Name</label>
        <input
          id="name"
          class="form-control"
          v-model="form.name"
          type="text"
          required
          placeholder="bc_rikko">
      </div>
      <div class="form-group">
        <label>Birth date</label>
        <input
          id="birth"
          class="form-control"
          v-model="form.birth"
          type="date">
      </div>
      <div class="form-group">
        <label>Age</label>
        <input
          id="age"
          class="form-control"
          v-model="form.age"
          type="number"
          min="0"
          max="999"
          placeholder="19">
      </div>
      <div class="form-group">
        <label>Email address</label>
        <input
          id="email"
          class="form-control"
          v-model="form.email"
          type="email"
          placeholder="love-vuejs@example.com">
      </div>
      <div class="form-group">
        <label>Blog URL</label>
        <input
          id="url"
          class="form-control"
          v-model="form.blog"
          type="url"
          placeholder="https://kuroeveryday.blogspot.com">
      </div>
      <div class="form-group">
        <label>ID</label>
        <input
          id="id"
          class="form-control"
          v-model="form.id"
          type="text"
          title="Custome ID(xxxx9999)"
          pattern="^[a-z]{4}[0-9]{4}$"
          placeholder="xxxx9999">
      </div>
      <div class="form-group">
        <label>Job</label>
        <input type="checkbox" id="jobA" value="Jack" v-model="form.jobA">
        <label for="jobA">jobA</label>
        <input type="checkbox" id="jobB" value="John" v-model="form.jobB">
        <label for="jobB">jobB</label>
        <input type="checkbox" id="jobC" value="Mike" v-model="form.jobC">
        <label for="jobC">jobC</label>
        <br>
      </div>
      <div class="form-group">
        <label>Gender</label>
        <input type="radio" id="Male" value="Male" name="gender" v-model="form.gender">
        <label for="Male">Male</label>
        <input type="radio" id="Female" value="Female" name="gender" v-model="form.gender">
        <label for="Female">Female</label>
        <br>
      </div>
      <div class="form-group">
         <label>location</label>
         <select v-model="form.location" id="location">
           <option disabled value="">Please select one</option>
           <option value="A">A</option>
           <option value="B">B</option>
           <option value="C">C</option>
         </select>
       </div>

      <div class="form-group">
        <button
          class="btn btn-primary"
          type="submit">Submit</button>
      </div>
    </form>
    <pre>{{ result }}</pre>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        form: {},
        result: {}
      }
    },

    methods: {
      onsubmit () {
        this.result = Object.assign({}, this.form);
      }
    }
  }

</script>

<style scoped>
  body {
    padding: 20px;
  }
</style>
