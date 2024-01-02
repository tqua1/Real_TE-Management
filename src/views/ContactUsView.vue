<template>
  <div class="main">
  <h2 class="contact-header"> Contact Us!</h2>

  <div class="contact-section">

    <h4 class="contact-info " > <i class="bi bi-envelope-at"></i>
      <div>Email Us:</div>
      <div>office@realTEmanagement.com</div>
    </h4>
    
    <h4 class="contact-info"> <i class="bi bi-telephone-forward"></i>
      <div>Call Us:</div>
      <div>888-888-8888</div>
    </h4>
   
    <h4 class="contact-info"> <i class="bi bi-mailbox-flag"></i>
      <div>Our Mailing Address:</div>
      <div>123 Tech Rd. Philadelphia, NY 90210</div>
    </h4>
    </div>
    </div>
  </template>
  
  <script>
  export default {
  
  }
  </script>
  
  <style>
  .main{
   border-style: groove;
  }
  .contact-section{
    
display: flex;
 justify-content: center;
  
 
  }
  .contact-info{
    padding: 1%;
    border-style: groove;
    margin: 3%;
    height: 20%;
    margin-left: 10%
    border-bottom
  }

 
  .contact-header {
    display: flex;
    justify-content: center;
    margin: 5%;
    margin-bottom: 1px;
  }
  .email-section{
    /* background:  #6f828e;
    padding: 10px;
    border-radius: .75rem;
  border: 1px; */
  /* color: rgba(255, 255, 255, 0.87);
  background-color: #6f828e;;
  border-color: #6f828e;;
  
  text-decoration: none;
  padding: 6px 12px; */

  }
  .phn-number-section{
    /* color: rgba(255, 255, 255, 0.87);
  background-color: #6f828e;;
  border-color: #6f828e;;
  text-decoration: none;
  padding: 6px 12px; */
  }
  .mailing-address-section{
    /* color: rgba(255, 255, 255, 0.87);
  background-color: #6f828e;;
  border-color: #6f828e;;
  text-decoration: none;
  padding: 6px 12px; */
  }
  </style>