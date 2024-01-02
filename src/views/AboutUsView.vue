<!-- Reference:
  https://www.w3schools.com/howto/howto_css_team.asp -->
<template>
  <div class="about-section ">
    <h1 style="text-align:center">About Us</h1>
    <p>Welcome to Real-TE Management, your dedicated ally in property excellence! Real-TE is more than just a management service; it's a partnership crafted to elevate your property experience, whether you're an owner or a tenant. Our journey began with a vision to redefine property standards, and today, we proudly stand as your reliable companion in the world of real estate.</p>
    <p>Why choose Real-TE? Imagine a seamless blend of professionalism and personalized care. For property owners, we understand that your property is not merely a structure; it's an investment in your future. Our seasoned team of experts is committed to nurturing your property's potential, ensuring it thrives under our dedicated care.
    As a tenant, picture a living space that feels like home from day one. Real-TE is here to create harmonious environments where your comfort and satisfaction take center stage. From prompt responses to maintenance requests to fostering a sense of community, we strive to make your living experience as delightful as possible.
    </p>
    <p>At Real-TE, transparency, communication, and proactive support are our cornerstones. We believe in empowering property owners and tenants alike with the information they need to make informed decisions. Whether it's addressing concerns promptly or enhancing your property's overall value, Real-TE is your trusted partner every step of the way.
    Join us on a journey where your property aspirations become a reality. Real-TE Management is not just about managing spaces; it's about co-authoring success stories with property owners and tenants alike. Discover the difference with Real-TE — where your property is more than a structure; it's a place where stories unfold, and we're here to help script them with you.
  </p>
    </div>
    <h2 style="text-align:center">Meet Our Founders: </h2>
    <div class="row">
  <div class="column">
    <div class="card">
      <img src="../assets/keenaHeadshot2.jpeg" style="width:100%">
      <div class="container">
        <h2>Keena Carulla</h2>
        <p class="title">Founder/Developer</p>
        <p>New York, NY</p>
        <p>keenacarulla@gmail.com</p>
      </div>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <img src="../assets/HeadshotShannonS.jpg" style="width:100%">
      <div class="container">
        <h2>Shannon Satterfield</h2>
        <p class="title">Founder/Developer</p>
        <p>Long Beach, CA</p>
        <p>ss.shannon.satterfield@gmail.com</p>
      </div>
    </div>
  </div>

  <div class="column ">
    <div class="card">
      <img src="../assets/taneaHeadshot.jpg"  style="width:100%">
      <div class="container">
        <h2>Tanea Quarles</h2>
        <p class="title">Founder/Developer</p>
        <p>Philadelphia, PA</p>
        <p>tquarle@gmail.com</p>
      </div>
    </div>
  </div>
</div>
</template>
  
  <script>
  export default {
  
  }
  </script>
  

<style scoped>
 body {
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
}

html {
  box-sizing: border-box;
}

*, *:before, *:after {
  box-sizing: inherit;
}

.column {
  float: left;
  width: 33.3%;
  margin-bottom: 60px;
  padding: 0 8px;
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  margin: 8px;
}

.about-section {
  padding: 50px;
  text-align: center;
  background-color: #d9d9d9;
  color: black;
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

@media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}

  </style>