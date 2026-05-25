<template>
  <div class="post-container">
    <el-header class="header">
      <el-button @click="$router.back()">返回</el-button>
      <div>
        <el-button type="warning" @click="handleDelete" v-if="post.id">删除</el-button>
      </div>
    </el-header>
    <el-main>
      <h1>{{ post.title }}</h1>
      <p class="post-meta">{{ post.createTime }}</p>
      <el-divider />
      <div class="post-content" v-html="post.content"></div>
    </el-main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getPost, deletePost } from '../api/post'

const route = useRoute()
const router = useRouter()
const post = ref({})

onMounted(async () => {
  try {
    post.value = await getPost(route.params.id)
  } catch (e) {
    ElMessage.error('加载文章失败')
  }
})

const handleDelete = async () => {
  try {
    await ElMessageBox.confirm('确定删除这篇文章吗？', '提示', { type: 'warning' })
    await deletePost(post.value.id)
    ElMessage.success('删除成功')
    router.push('/')
  } catch (e) {
    // cancelled or error
  }
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
.post-meta {
  color: #999;
  font-size: 14px;
}
.post-content {
  line-height: 1.8;
}
</style>
