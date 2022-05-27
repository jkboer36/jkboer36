<template>
    <div class="not-found">
        <img src="../assets/404-Page-Not-Found-Errors.jpg" alt="">
    </div>
</template>
<script>
export default {
    name:"not-found",
    components:{},
}
</script>

<style scoped>
.not-found{
    width:100%;
    height:100%;
    overflow:hidden;
    text-align: center;
}
.not-found img{
    position: absolute; 
    left:25%; 
    top:30%; 
    width:50%;
    height:50%;
}
</style>