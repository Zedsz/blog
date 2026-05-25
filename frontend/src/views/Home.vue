<template>
  <div class="home-container">
    <el-header class="header">
      <h1>我的博客</h1>
      <div>
        <el-button type="primary" @click="$router.push('/write')">写文章</el-button>
        <el-button @click="handleLogout">退出登录</el-button>
      </div>
    </el-header>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="6" v-for="post in posts" :key="post.id">
          <el-card class="post-card" @click="$router.push(`/post/${post.id}`)">
            <h3>{{ post.title }}</h3>
            <p class="post-summary">{{ post.summary }}</p>
            <span class="post-date">{{ post.createTime }}</span>
          </el-card>
        </el-col>
      </el-row>
      <el-empty v-if="posts.length === 0" description="还没有文章" />
    </el-main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getPosts } from '../api/post'

const router = useRouter()
const posts = ref([])

onMounted(async () => {
  try {
    posts.value = await getPosts()
  } catch (e) {
    // 401 handled by interceptor
  }
})

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/login')
}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 24px;
  border-bottom: 1px solid #eee;
  background: #fff;
}
.post-card {
  cursor: pointer;
  margin-bottom: 16px;
}
.post-card:hover {
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}
.post-summary {
  color: #666;
  font-size: 14px;
}
.post-date {
  color: #999;
  font-size: 12px;
}
</style>
