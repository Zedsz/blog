<template>
  <div class="write-container">
    <el-header class="header">
      <el-button @click="$router.back()">返回</el-button>
      <h1>写文章</h1>
      <el-button type="primary" @click="handlePublish" :loading="publishing">发布</el-button>
    </el-header>
    <el-main>
      <el-input v-model="title" placeholder="文章标题" size="large" class="title-input" />
      <div class="editor-wrapper">
        <el-input
          v-model="content"
          type="textarea"
          :rows="20"
          placeholder="文章内容 (支持 Markdown)"
        />
      </div>
    </el-main>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { createPost } from '../api/post'

const router = useRouter()
const title = ref('')
const content = ref('')
const publishing = ref(false)

const handlePublish = async () => {
  if (!title.value.trim()) {
    ElMessage.warning('请输入标题')
    return
  }
  if (!content.value.trim()) {
    ElMessage.warning('请输入内容')
    return
  }

  publishing.value = true
  try {
    await createPost({ title: title.value, content: content.value })
    ElMessage.success('发布成功')
    router.push('/')
  } catch (e) {
    ElMessage.error('发布失败')
  } finally {
    publishing.value = false
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
.title-input {
  margin-bottom: 16px;
}
.editor-wrapper {
  margin-top: 16px;
}
</style>
