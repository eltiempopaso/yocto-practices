#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/proc_fs.h>
#include <linux/seq_file.h>

#define PROC_NAME "hello_proc"

static int hello_proc_show(struct seq_file *m, void *v)
{
    seq_printf(m, "Hello world from kernel module!\n");
    return 0;
}

static int hello_proc_open(struct inode *inode, struct file *file)
{
    return single_open(file, hello_proc_show, NULL);
}

static const struct proc_ops hello_proc_ops = {
    .proc_open    = hello_proc_open,
    .proc_read    = seq_read,
    .proc_lseek   = seq_lseek,
    .proc_release = single_release,
};

static int __init hello_proc_init(void)
{
    proc_create(PROC_NAME, 0, NULL, &hello_proc_ops);
    pr_info("hello_proc: module loaded\n");
    return 0;
}

static void __exit hello_proc_exit(void)
{
    remove_proc_entry(PROC_NAME, NULL);
    pr_info("hello_proc: module unloaded\n");
}

module_init(hello_proc_init);
module_exit(hello_proc_exit);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Oriol");
MODULE_DESCRIPTION("Hello World kernel module with /proc");

